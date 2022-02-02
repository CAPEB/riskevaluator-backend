package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.QuestionnaireEntity;
import fr.capeb.backend.riskevaluator.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionnaireRepository extends JpaRepository<QuestionnaireEntity, Integer> {

}
