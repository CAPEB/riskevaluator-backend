package fr.capeb.backend.riskevaluator.service.interfaces;

import fr.capeb.backend.riskevaluator.dto.PreconisationCategorie;
import fr.capeb.backend.riskevaluator.dto.Questionnaire;

import java.util.Optional;
import java.util.Set;

public interface PreconisationCategorieService {
    Set<PreconisationCategorie> getAllPreconisationCategorie();
    Optional<PreconisationCategorie> getPreconisationCategorieById(Integer quesId);
    Optional<PreconisationCategorie> createOrUpdatePreconisationCategorie(PreconisationCategorie obj);
    Optional<Object> deletePreconisationCategorie(Integer quesId);

}
