package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.QuestionEntity;
import fr.capeb.backend.riskevaluator.model.QuestionnaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
}
