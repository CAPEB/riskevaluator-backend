package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.MetierQuestionEntityPK;
import fr.capeb.backend.riskevaluator.model.ScoreCategoryEntity;
import fr.capeb.backend.riskevaluator.model.ScoreCategoryEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreCategorieRepository extends JpaRepository<ScoreCategoryEntity, ScoreCategoryEntityPK> {
}
