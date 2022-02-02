package fr.capeb.backend.riskevaluator.service.interfaces;

import fr.capeb.backend.riskevaluator.dto.Questionnaire;

import java.util.Optional;
import java.util.Set;

public interface QuestionnaireService {
     Set<Questionnaire> getAllQuestionnaires();
     Optional<Questionnaire> getQuestionnaireById(Integer quesId);
     Optional<Questionnaire> createOrUpdateQuestionnaire(Questionnaire questionnaire);

}
