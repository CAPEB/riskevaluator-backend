package fr.capeb.backend.riskevaluator.service.interfaces;

import fr.capeb.backend.riskevaluator.dto.CategorieQuestion;
import fr.capeb.backend.riskevaluator.dto.Questionnaire;

import java.util.Optional;
import java.util.Set;

public interface CategorieQuestionService {
     Set<CategorieQuestion> getAllCategorieQuestion();
     Optional<CategorieQuestion> categorieQuestionById(Integer quesId);
     Optional<Object> deleteCategorieQuestion(Integer id);
     Optional<CategorieQuestion> updateCategorieQuestion(CategorieQuestion obj);
}
