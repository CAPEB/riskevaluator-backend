package fr.capeb.backend.riskevaluator.dto;

import fr.capeb.backend.riskevaluator.model.CompteEntity;
import fr.capeb.backend.riskevaluator.model.ScoreCategoryEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Evaluation {

    private Integer idEvaluation;
    private Integer idCompte;
    private List<ScoreCategory> scoreCategories;


}
