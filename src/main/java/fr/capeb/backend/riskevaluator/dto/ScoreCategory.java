package fr.capeb.backend.riskevaluator.dto;

import lombok.*;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class ScoreCategory {
    @NotNull
    private Evaluation evaluation;
    @NotNull
    private CategorieQuestion categorie;
    @NotNull
    private Integer nbPoints;

}
