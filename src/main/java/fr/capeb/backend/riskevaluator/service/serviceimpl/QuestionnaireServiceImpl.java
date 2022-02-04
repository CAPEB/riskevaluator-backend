package fr.capeb.backend.riskevaluator.service.serviceimpl;

import fr.capeb.backend.riskevaluator.dto.CategorieQuestion;
import fr.capeb.backend.riskevaluator.dto.Questionnaire;
import fr.capeb.backend.riskevaluator.exceptions.model.ConflictException;
import fr.capeb.backend.riskevaluator.exceptions.model.CreateOrUpdateException;
import fr.capeb.backend.riskevaluator.exceptions.model.MappingDataException;
import fr.capeb.backend.riskevaluator.model.CategorieQuestionEntity;
import fr.capeb.backend.riskevaluator.model.QuestionnaireEntity;
import fr.capeb.backend.riskevaluator.repository.QuestionCategorieRepository;
import fr.capeb.backend.riskevaluator.repository.QuestionnaireRepository;
import fr.capeb.backend.riskevaluator.service.interfaces.QuestionnaireService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnairesRepo ;
    @Autowired
    private QuestionCategorieRepository questionCategorieRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Set<Questionnaire> getAllQuestionnaires() {
        return questionnairesRepo.findAll()
                .stream()
                .map(stop -> modelMapper.map(stop, Questionnaire.class))
                .collect(Collectors.toSet());
    }

    public Optional<Questionnaire> getQuestionnaireById(Integer quesId) {


        if(quesId == null) return Optional.empty();

        Optional<QuestionnaireEntity> questionnaire = questionnairesRepo.findById(quesId);
        if (questionnaire.isPresent()) {
            return Optional.of(modelMapper.map(questionnaire.get(),Questionnaire.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Questionnaire> createOrUpdateQuestionnaire(Questionnaire questionnaire) {


        var isConflict = questionnairesRepo.findByThematique(questionnaire.getThematique()).isPresent();
        if(isConflict) throw new ConflictException();

        var ques = Optional.of(modelMapper.map(questionnaire, QuestionnaireEntity.class)).orElseThrow(MappingDataException::new);
        var updated = Optional.of(questionnairesRepo.save(ques)).orElseThrow(CreateOrUpdateException::new);
        return Optional.of(modelMapper.map(updated,Questionnaire.class));

    }

    @Override
    public Optional<Object> deleteQuestionnaire(Integer quesId) {
        questionnairesRepo.deleteById(quesId);
        return Optional.empty();
    }




}
