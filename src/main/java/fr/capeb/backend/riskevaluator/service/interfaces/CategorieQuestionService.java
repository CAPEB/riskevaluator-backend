package fr.capeb.backend.riskevaluator.service.interfaces;

import fr.capeb.backend.riskevaluator.dto.CategorieQuestion;

import java.util.List;
import java.util.Optional;

public interface CategorieQuestionService {
     List<CategorieQuestion> getAllCategorieQuestion();
     Optional<CategorieQuestion> categorieQuestionById(Integer quesId);
     Optional<Object> deleteCategorieQuestion(Integer id);
     Optional<CategorieQuestion> createCategorieQuestion(CategorieQuestion obj);
     Optional<CategorieQuestion> updateCategorieQuestion(CategorieQuestion aCategorieQuestion);
}
