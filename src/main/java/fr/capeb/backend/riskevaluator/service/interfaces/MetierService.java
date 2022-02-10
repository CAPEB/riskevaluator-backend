package fr.capeb.backend.riskevaluator.service.interfaces;

import fr.capeb.backend.riskevaluator.dto.Metier;
import fr.capeb.backend.riskevaluator.dto.Question;

import java.util.Optional;
import java.util.Set;

public interface MetierService {
     Set<Metier> getAllMetier();
     Optional<Metier> getMetierById(Integer quesId);
     Optional<Metier> createOrUpdateMetier(Metier metier);
     Optional<Object> deleteMetierById(Integer quesId);
}
