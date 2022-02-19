package fr.capeb.backend.riskevaluator.service.serviceimpl;

import fr.capeb.backend.riskevaluator.dto.CategorieQuestion;
import fr.capeb.backend.riskevaluator.exceptions.model.CreateOrUpdateException;
import fr.capeb.backend.riskevaluator.exceptions.model.CustomException;
import fr.capeb.backend.riskevaluator.exceptions.model.MappingDataException;
import fr.capeb.backend.riskevaluator.model.CategorieQuestionEntity;
import fr.capeb.backend.riskevaluator.repository.QuestionCategorieRepository;
import fr.capeb.backend.riskevaluator.repository.QuestionnaireRepository;
import fr.capeb.backend.riskevaluator.service.interfaces.CategorieQuestionService;
import fr.capeb.backend.riskevaluator.service.interfaces.PreconisationCategorieService;
import fr.capeb.backend.riskevaluator.service.interfaces.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static fr.capeb.backend.riskevaluator.exceptions.ExceptionMsg.ID_NOT_FOUND;

@Component

public class CategorieQuestionServiceImpl implements CategorieQuestionService {

    @Autowired
    private QuestionCategorieRepository questionCategorieRepo;

    @Autowired
    private QuestionnaireRepository questionnaireRepo;

    @Autowired
    private QuestionService pQuestionManager;

    @Autowired
    private PreconisationCategorieService pPreconisationCategorieManager;

    @Autowired
    private ModelMapper modelMapper;

    public Set<CategorieQuestion> getAllCategorieQuestion() {
        return questionCategorieRepo.findAll()
                .stream()
                .map(stop -> modelMapper.map(stop, CategorieQuestion.class))
                .collect(Collectors.toSet());
    }



    @Override
    public Optional<CategorieQuestion> categorieQuestionById(Integer quesId) {

        if(quesId == null) return Optional.empty();

        var questionnaire = questionCategorieRepo.findById(quesId);
        if (questionnaire.isPresent()) {
            return Optional.of(modelMapper.map(questionnaire.get(),CategorieQuestion.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Object> deleteCategorieQuestion(Integer aCategorieQuestionId) {
        var wCategorieQuestionEntity = questionCategorieRepo.getById(aCategorieQuestionId);
        var wPreconisationIdList=wCategorieQuestionEntity.getPreconisationsCategorie().stream().map(wPreconisationCategorie->wPreconisationCategorie.getIdPreconisation()).collect(Collectors.toList());
        wPreconisationIdList.forEach(wPreconisationId->pPreconisationCategorieManager.deletePreconisationCategorie(wPreconisationId));
        wCategorieQuestionEntity.getQuestionnaire().getCategorieQuestions().remove(wCategorieQuestionEntity);
        var wQuestionIdsToRemove=wCategorieQuestionEntity.getQuestions().stream().map(wQuestion->wQuestion.getIdQuestion()).collect(Collectors.toList());
        wQuestionIdsToRemove.forEach(wQuestionId->pQuestionManager.deleteQuestionById(wQuestionId));
        questionCategorieRepo.delete(wCategorieQuestionEntity);
        return Optional.empty();
    }

    @Override
    public Optional<CategorieQuestion> createCategorieQuestion(CategorieQuestion aCategorieQuestion) {

        var wQuestionnaireEntity =  questionnaireRepo.findById(aCategorieQuestion
                        .getQuestionnaire().getIdQuestionnaire())
                .orElseThrow(()-> new CustomException("Questionnaire"+ID_NOT_FOUND.value));

        var wCategorieQuestionEntity = Optional
                .of(modelMapper
                        .map(aCategorieQuestion, CategorieQuestionEntity.class))
                .orElseThrow(MappingDataException::new);


        wCategorieQuestionEntity.setQuestionnaire(wQuestionnaireEntity);
        var wCreatedCategorieQuestionEntity = Optional
                .of(questionCategorieRepo
                        .save(wCategorieQuestionEntity))
                .orElseThrow(CreateOrUpdateException::new);
        wQuestionnaireEntity.getCategorieQuestions().add(wCreatedCategorieQuestionEntity);


        return Optional.of(modelMapper.map(wCreatedCategorieQuestionEntity,CategorieQuestion.class));

    }

    @Override
    public Optional<CategorieQuestion> updateCategorieQuestion(CategorieQuestion aCategorieQuestion) {

        var wCategorieQuestionEntity = questionCategorieRepo.getById(aCategorieQuestion.getIdCategorie());
        wCategorieQuestionEntity.setLibelle(aCategorieQuestion.libelle);
        var wCreatedCategorieQuestionEntity = Optional
                .of(questionCategorieRepo
                        .save(wCategorieQuestionEntity))
                .orElseThrow(CreateOrUpdateException::new);


        return Optional.of(modelMapper.map(wCreatedCategorieQuestionEntity,CategorieQuestion.class));

    }

}
