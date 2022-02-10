package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.PreconisationCategorieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PreconisationCategorieRepository extends JpaRepository<PreconisationCategorieEntity, Integer> {
}
