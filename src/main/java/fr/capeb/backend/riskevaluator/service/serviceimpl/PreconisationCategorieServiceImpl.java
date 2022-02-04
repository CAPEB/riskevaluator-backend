package fr.capeb.backend.riskevaluator.service.serviceimpl;

import fr.capeb.backend.riskevaluator.dto.*;
import fr.capeb.backend.riskevaluator.exceptions.ExceptionMsg;
import fr.capeb.backend.riskevaluator.exceptions.model.CreateOrUpdateException;
import fr.capeb.backend.riskevaluator.exceptions.model.CustomException;
import fr.capeb.backend.riskevaluator.exceptions.model.MappingDataException;
import fr.capeb.backend.riskevaluator.model.PreconisationCategorieEntity;
import fr.capeb.backend.riskevaluator.repository.PreconisationCategorieRepository;
import fr.capeb.backend.riskevaluator.repository.QuestionCategorieRepository;
import fr.capeb.backend.riskevaluator.service.interfaces.PreconisationCategorieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional
public class PreconisationCategorieServiceImpl implements PreconisationCategorieService {
    @Autowired
    private PreconisationCategorieRepository preCatRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private QuestionCategorieRepository questionCategorieRepo;

    @Override
    public Set<PreconisationCategorie> getAllPreconisationCategorie() {
        return preCatRepo.findAll()
                .stream()
                .map(stop -> modelMapper.map(stop, PreconisationCategorie.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<PreconisationCategorie> getPreconisationCategorieById(Integer quesId) {

        if(quesId == null) return Optional.empty();

        var questionnaire = preCatRepo.findById(quesId);
        if (questionnaire.isPresent()) {
            return Optional.of(modelMapper.map(questionnaire.get(),PreconisationCategorie.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<PreconisationCategorie> createOrUpdatePreconisationCategorie(PreconisationCategorie obj) {
        var prec = Optional.of(modelMapper.map(obj, PreconisationCategorieEntity.class)).orElseThrow(MappingDataException::new);
        var updated = Optional.of(preCatRepo.save(prec)).orElseThrow(CreateOrUpdateException::new);
        return Optional.of(modelMapper.map(updated, PreconisationCategorie.class));
    }

    @Override
    public Optional<Object> deletePreconisationCategorie(Integer quesId) {
        preCatRepo.deleteById(quesId);
        return Optional.empty();
    }
}
