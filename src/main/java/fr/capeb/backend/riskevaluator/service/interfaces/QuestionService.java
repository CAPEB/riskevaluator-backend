package fr.capeb.backend.riskevaluator.service.interfaces;

import fr.capeb.backend.riskevaluator.dto.CategorieQuestion;
import fr.capeb.backend.riskevaluator.dto.Question;
import fr.capeb.backend.riskevaluator.dto.Questionnaire;
import fr.capeb.backend.riskevaluator.model.QuestionEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface QuestionService {
     Set<Question> getAllQuestion();
     Optional<Question> getQuestionById(Integer quesId);
     Optional<Question> createOrUpdateQuestion(Question question);
     Optional<Object> deleteQuestion(Integer quesId);
}
