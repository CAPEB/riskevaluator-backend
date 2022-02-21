package fr.capeb.backend.riskevaluator.service.interfaces;

import fr.capeb.backend.riskevaluator.dto.CategorieQuestion;
import fr.capeb.backend.riskevaluator.dto.Metier;
import fr.capeb.backend.riskevaluator.dto.Questionnaire;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface QuestionnaireService {
     List<Questionnaire> getAllQuestionnaires();
     Optional<Questionnaire> getQuestionnaireById(Integer quesId);
     Optional<Questionnaire> createQuestionnaire(Questionnaire questionnaire);

     Optional<Questionnaire> UpdateQuestionnaire(Questionnaire questionnaire);

     Optional<Object> deleteQuestionnaire(Integer quesId);
     List<Metier> getMetiersByQuestionnaireId(Integer aQuestionnaireId);
     List<CategorieQuestion> getCategorieQuestionsByQuestionnaireIdAndMetiers(Integer aQuestionnaireId, Set<Integer> metierIds);

}
