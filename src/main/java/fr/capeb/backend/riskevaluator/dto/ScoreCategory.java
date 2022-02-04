package fr.capeb.backend.riskevaluator.dto;

import fr.capeb.backend.riskevaluator.model.CategorieQuestionEntity;
import fr.capeb.backend.riskevaluator.model.EvaluationEntity;
import fr.capeb.backend.riskevaluator.model.ScoreCategoryEntityPK;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class ScoreCategory {

    private Integer idEvaluation;
    private Integer idCategorie;
    private Integer nbPoints;

}
