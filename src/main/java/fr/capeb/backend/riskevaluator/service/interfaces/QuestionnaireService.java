package fr.capeb.backend.riskevaluator.service.interfaces;

import fr.capeb.backend.riskevaluator.dto.Questionnaire;

import java.util.Set;

public interface QuestionnaireService {
     Set<Questionnaire> getAllQuestionnaires();
}
