package fr.capeb.backend.riskevaluator.service.interfaces;

import fr.capeb.backend.riskevaluator.dto.Questionnaire;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface QuestionnaireService {
     List<Questionnaire> getAllQuestionnaires();
     Optional<Questionnaire> getQuestionnaireById(Integer quesId);
     Optional<Questionnaire> createOrUpdateQuestionnaire(Questionnaire questionnaire);
     Optional<Object> deleteQuestionnaire(Integer quesId);

}
