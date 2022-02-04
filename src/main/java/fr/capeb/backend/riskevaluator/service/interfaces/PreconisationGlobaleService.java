package fr.capeb.backend.riskevaluator.service.interfaces;

import fr.capeb.backend.riskevaluator.dto.PreconisationCategorie;
import fr.capeb.backend.riskevaluator.dto.PreconisationGlobale;

import java.util.Optional;
import java.util.Set;

public interface PreconisationGlobaleService {
    Set<PreconisationGlobale> getAllPreconisationGlobale();
    Optional<PreconisationGlobale> getPreconisationGlobaleById(Integer quesId);
    Optional<PreconisationGlobale> createOrUpdatePreconisationGlobale(PreconisationGlobale obj);
    Optional<Object> deletePreconisationGlobale(Integer quesId);

}
