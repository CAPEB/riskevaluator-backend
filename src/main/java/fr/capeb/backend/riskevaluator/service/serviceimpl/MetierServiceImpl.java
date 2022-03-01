package fr.capeb.backend.riskevaluator.service.serviceimpl;

import fr.capeb.backend.riskevaluator.dto.Metier;
import fr.capeb.backend.riskevaluator.dto.Questionnaire;
import fr.capeb.backend.riskevaluator.exceptions.model.CreateOrUpdateException;
import fr.capeb.backend.riskevaluator.exceptions.model.MappingDataException;
import fr.capeb.backend.riskevaluator.model.MetierEntity;
import fr.capeb.backend.riskevaluator.repository.MetierQuestionRepository;
import fr.capeb.backend.riskevaluator.repository.MetierRepository;
import fr.capeb.backend.riskevaluator.repository.QuestionnaireRepository;
import fr.capeb.backend.riskevaluator.service.interfaces.MetierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional
public class MetierServiceImpl implements MetierService {

    @Autowired
    private MetierRepository metierRepo ;

    @Autowired
    private QuestionnaireRepository pQuestionnaireRepository;

    @Autowired
    private MetierQuestionRepository pMetierQuestionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Metier> getAllMetier() {
            return metierRepo.findAll(Sort.by(Sort.Direction.ASC,"nomMetier"))
                .stream()
                .map(stop -> modelMapper.map(stop, Metier.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Metier> getMetierById(Integer quesId) {
        if(quesId == null) return Optional.empty();


        Optional<MetierEntity> metier = metierRepo.findById(quesId);
        if (metier.isPresent()) {
            return Optional.of(modelMapper.map(metier.get(),Metier.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Metier> createOrUpdateMetier(Metier aMetier) {
        MetierEntity wMetierEntity;
        if(metierRepo.findById(aMetier.getIdMetier()).isPresent()) {
             wMetierEntity = metierRepo.getById(aMetier.getIdMetier());
            wMetierEntity.setNomMetier(aMetier.getNomMetier());
        } else {
            wMetierEntity = Optional.of(modelMapper.map(aMetier, MetierEntity.class)).orElseThrow(MappingDataException::new);
        }
        var updated = Optional.of(metierRepo.save(wMetierEntity)).orElseThrow(CreateOrUpdateException::new);
        return Optional.of(modelMapper.map(updated, Metier.class));
    }

    @Override
    public Optional<Object> deleteMetierById(Metier aMetier) {
        var wMetierEntity=metierRepo.getById(aMetier.getIdMetier());
        wMetierEntity.getQuestions().forEach(
                wMetierQuestionEntity
                ->wMetierQuestionEntity
                        .getQuestion().getMetiers().removeAll(wMetierEntity.getQuestions()));
        wMetierEntity.getQuestions().removeAll(wMetierEntity.getQuestions());
        pMetierQuestionRepository.deleteAll(wMetierEntity.getQuestions());
        pMetierQuestionRepository.flush();
        metierRepo.delete(wMetierEntity);
        return Optional.empty();
    }

    @Override
    public List<Questionnaire> getQuestionnaireByListMetierId(Set<Integer> aMetierIds) {
        var wMetiers=metierRepo.findAllById(aMetierIds).stream().map(metier->modelMapper.map(metier,Metier.class)).collect(Collectors.toList());

        var wQuestionnaireList=pQuestionnaireRepository.getQuestionnaireByMetiersIds(aMetierIds)
                .stream().map(wQuestionnaireEntity -> modelMapper.map(wQuestionnaireEntity,Questionnaire.class))
                .collect(Collectors.toList());
        wQuestionnaireList.forEach(questionnaire->questionnaire.setCategorieQuestions(questionnaire.getCategorieQuestions().stream().filter(wCategorieQuestion -> {
            wCategorieQuestion.setQuestions(
                    wCategorieQuestion.getQuestions().stream().filter(
                            question ->{
                               var ret= question.getMetiers().containsAll(wMetiers);
                               return ret;
                            } ).collect(Collectors.toList()));
            return wCategorieQuestion.getQuestions().size()>0;
        }).collect(Collectors.toList())));

        return wQuestionnaireList;
    }
}
