package fr.capeb.backend.riskevaluator.service.interfaces;

import fr.capeb.backend.riskevaluator.dto.Question;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface QuestionService {
     List<Question> getAllQuestion();
     Optional<Question> getQuestionById(Integer quesId);
     Optional<Question> createOrUpdateQuestion(Question question);
     Optional<Object> deleteQuestionById(Integer quesId);
}
