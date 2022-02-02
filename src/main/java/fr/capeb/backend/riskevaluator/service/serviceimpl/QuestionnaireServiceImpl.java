package fr.capeb.backend.riskevaluator.service.serviceimpl;

import fr.capeb.backend.riskevaluator.dto.Questionnaire;
import fr.capeb.backend.riskevaluator.exceptions.model.CreateOrUpdateException;
import fr.capeb.backend.riskevaluator.exceptions.model.MappingDataException;
import fr.capeb.backend.riskevaluator.model.QuestionnaireEntity;
import fr.capeb.backend.riskevaluator.repository.QuestionnaireRepository;
import fr.capeb.backend.riskevaluator.service.interfaces.QuestionnaireService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Component
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnairesRepo ;

    @Autowired
    private ModelMapper modelMapper;

    public Set<Questionnaire> getAllQuestionnaires() {
        return questionnairesRepo.findAll()
                .stream()
                .map(stop -> modelMapper.map(stop, Questionnaire.class))
                .collect(Collectors.toSet());
    }

    public Optional<Questionnaire> getQuestionnaireById(Integer quesId) {


        Optional<QuestionnaireEntity> questionnaire = questionnairesRepo.findById(quesId);
        if (questionnaire.isPresent()) {
            return Optional.of(modelMapper.map(questionnaire.get(),Questionnaire.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Questionnaire> createOrUpdateQuestionnaire(Questionnaire questionnaire) {

        Optional<QuestionnaireEntity> quesEntity = Optional.of(modelMapper.map(questionnaire,QuestionnaireEntity.class));

        if(quesEntity.isEmpty()) throw new MappingDataException();

        Optional<QuestionnaireEntity> updated = Optional.of(questionnairesRepo.save(quesEntity.get()));

        return Optional.of(modelMapper.map(updated.get(),Questionnaire.class));
    }
}
