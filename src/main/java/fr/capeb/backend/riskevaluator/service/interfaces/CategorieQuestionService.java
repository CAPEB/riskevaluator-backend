package fr.capeb.backend.riskevaluator.service.interfaces;

import fr.capeb.backend.riskevaluator.dto.CategorieQuestion;

import java.util.Optional;
import java.util.Set;

public interface CategorieQuestionService {
     Set<CategorieQuestion> getAllCategorieQuestion();
     Optional<CategorieQuestion> categorieQuestionById(Integer quesId);
     Optional<Object> deleteCategorieQuestion(Integer id);
     Optional<CategorieQuestion> createCategorieQuestion(CategorieQuestion obj);
     Optional<CategorieQuestion> updateCategorieQuestion(CategorieQuestion aCategorieQuestion);
}
