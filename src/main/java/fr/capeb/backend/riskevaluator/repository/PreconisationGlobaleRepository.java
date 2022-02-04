package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.PreconisationCategorieEntity;
import fr.capeb.backend.riskevaluator.model.PreconisationGlobaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreconisationGlobaleRepository extends JpaRepository<PreconisationGlobaleEntity, Integer> {
}
