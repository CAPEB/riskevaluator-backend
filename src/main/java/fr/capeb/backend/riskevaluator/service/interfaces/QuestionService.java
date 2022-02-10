package fr.capeb.backend.riskevaluator.service.interfaces;

import fr.capeb.backend.riskevaluator.dto.Question;

import java.util.Optional;
import java.util.Set;

public interface QuestionService {
     Set<Question> getAllQuestion();
     Optional<Question> getQuestionById(Integer quesId);
     Optional<Question> createOrUpdateQuestion(Question question);
     Optional<Object> deleteQuestionById(Integer quesId);
}
