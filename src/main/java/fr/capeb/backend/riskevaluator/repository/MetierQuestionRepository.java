package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.MetierQuestionEntity;
import fr.capeb.backend.riskevaluator.model.MetierQuestionEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetierQuestionRepository extends JpaRepository<MetierQuestionEntity, MetierQuestionEntityPK> {
}
