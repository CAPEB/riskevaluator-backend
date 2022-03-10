package fr.capeb.backend.riskevaluator.service.interfaces;

import fr.capeb.backend.riskevaluator.dto.Reponse;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ReponseService {
    List<Reponse> getAllReponses();
    Optional<Reponse> getReponseById(Integer aReponseId);
    Optional<Reponse> createOrUpdateReponse(Reponse aReponse);

    Optional<Reponse> createReponse(Reponse aReponse);

    Optional<Object> deleteReponse(Integer aReponseId);
}
