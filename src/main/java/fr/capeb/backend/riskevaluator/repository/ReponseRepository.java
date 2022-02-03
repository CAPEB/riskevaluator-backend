package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.ReponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseRepository extends JpaRepository<ReponseEntity,Integer> {
}
