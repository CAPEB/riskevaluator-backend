package fr.capeb.backend.riskevaluator.service.serviceimpl;

import fr.capeb.backend.riskevaluator.dto.PreconisationCategorie;
import fr.capeb.backend.riskevaluator.dto.PreconisationGlobale;
import fr.capeb.backend.riskevaluator.exceptions.ExceptionMsg;
import fr.capeb.backend.riskevaluator.exceptions.model.CreateOrUpdateException;
import fr.capeb.backend.riskevaluator.exceptions.model.CustomException;
import fr.capeb.backend.riskevaluator.exceptions.model.MappingDataException;
import fr.capeb.backend.riskevaluator.model.PreconisationCategorieEntity;
import fr.capeb.backend.riskevaluator.model.PreconisationGlobaleEntity;
import fr.capeb.backend.riskevaluator.repository.PreconisationGlobaleRepository;
import fr.capeb.backend.riskevaluator.repository.QuestionCategorieRepository;
import fr.capeb.backend.riskevaluator.repository.QuestionnaireRepository;
import fr.capeb.backend.riskevaluator.service.interfaces.PreconisationGlobaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional
public class PreconisationGlobaleServiceImpl implements PreconisationGlobaleService {
    @Autowired
    private PreconisationGlobaleRepository preGlobRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private QuestionnaireRepository questionnaireRepo;

    @Override
    public Set<PreconisationGlobale> getAllPreconisationGlobale() {
        return preGlobRepo.findAll()
                .stream()
                .map(stop -> modelMapper.map(stop, PreconisationGlobale.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<PreconisationGlobale> getPreconisationGlobaleById(Integer quesId) {
        if(quesId == null) return Optional.empty();

        var questionnaire = preGlobRepo.findById(quesId);
        if (questionnaire.isPresent()) {
            return Optional.of(modelMapper.map(questionnaire.get(),PreconisationGlobale.class));
        }
        return Optional.empty();

    }

    @Override
    public Optional<PreconisationGlobale> createOrUpdatePreconisationGlobale(PreconisationGlobale obj) {
        questionnaireRepo.findById(obj.getIdQuestionnaire()).orElseThrow(()-> new CustomException("The questionnaire" + ExceptionMsg.ID_NOT_FOUND.value));

        var prec = Optional.of(modelMapper.map(obj, PreconisationGlobaleEntity.class)).orElseThrow(MappingDataException::new);
        var updated = Optional.of(preGlobRepo.save(prec)).orElseThrow(CreateOrUpdateException::new);
        return Optional.of(modelMapper.map(updated, PreconisationGlobale.class));
    }

    @Override
    public Optional<Object> deletePreconisationGlobale(Integer quesId) {
        preGlobRepo.deleteById(quesId);
        return Optional.empty();
    }
}
