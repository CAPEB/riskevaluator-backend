package fr.capeb.backend.riskevaluator.service.interfaces;

import fr.capeb.backend.riskevaluator.dto.CategorieQuestion;
import fr.capeb.backend.riskevaluator.dto.Metier;
import fr.capeb.backend.riskevaluator.dto.Question;
import fr.capeb.backend.riskevaluator.dto.Questionnaire;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface QuestionnaireService {
     Set<Questionnaire> getAllQuestionnaires();
     Optional<Questionnaire> getQuestionnaireById(Integer quesId);
     Optional<Questionnaire> createOrUpdateQuestionnaire(Questionnaire questionnaire);
     Optional<Object> deleteQuestionnaire(Integer quesId);
     Set<Metier> getMetiersByQuestionnaireId(Integer aQuestionnaireId);
     Set<CategorieQuestion> getQuestionsByQuestionnaireIdAndMetiers(Integer aQuestionnaireId, Set<Integer> metierIds);

}
