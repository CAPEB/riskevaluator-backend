package fr.capeb.backend.riskevaluator.service.interfaces;

import fr.capeb.backend.riskevaluator.dto.Reponse;
import java.util.Optional;
import java.util.Set;

public interface ReponseService {
    Set<Reponse> getAllReponses();
    Optional<Reponse> getReponseById(Integer aReponseId);
    Optional<Reponse> createOrUpdateReponse(Reponse aReponse);
    Optional<Object> deleteReponse(Integer aReponseId);
}
