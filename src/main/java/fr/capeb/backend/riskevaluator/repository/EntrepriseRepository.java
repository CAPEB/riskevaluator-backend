package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.EntrepriseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseRepository extends JpaRepository<EntrepriseEntity,Long> {
}
