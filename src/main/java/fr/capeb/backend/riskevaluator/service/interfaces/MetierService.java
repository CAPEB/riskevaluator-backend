package fr.capeb.backend.riskevaluator.service.interfaces;

import fr.capeb.backend.riskevaluator.dto.Metier;
import fr.capeb.backend.riskevaluator.dto.Question;
import fr.capeb.backend.riskevaluator.dto.Questionnaire;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MetierService {
     Set<Metier> getAllMetier();
     Optional<Metier> getMetierById(Integer aMetierId);
     Optional<Metier> createOrUpdateMetier(Metier aMetier);
     Optional<Object> deleteMetierById(Metier aMetier);
     Set<Questionnaire> getQuestionnaireByListMetierId(Set<Integer> aMetierIds);
}
