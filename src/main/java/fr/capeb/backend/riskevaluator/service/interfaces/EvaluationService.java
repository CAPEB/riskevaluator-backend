package fr.capeb.backend.riskevaluator.service.interfaces;

import fr.capeb.backend.riskevaluator.dto.Evaluation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EvaluationService {
    List<Evaluation> getAllEvaluation();
    Optional<Evaluation> getEvaluationById(Integer aEvaluationId);
    Optional<Object> deleteEvaluation(Integer aEvaluationId);
    Optional<Evaluation> SaveEvaluation(Evaluation aEvaluation);

}
