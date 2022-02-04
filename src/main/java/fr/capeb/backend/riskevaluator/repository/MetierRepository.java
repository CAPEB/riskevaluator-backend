package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.MetierEntity;
import fr.capeb.backend.riskevaluator.model.MetierQuestionEntityPK;
import fr.capeb.backend.riskevaluator.model.PreconisationCategorieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetierRepository extends JpaRepository<MetierEntity, Integer> {
}
