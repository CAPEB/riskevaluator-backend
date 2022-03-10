package fr.capeb.backend.riskevaluator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties({"scoreCategories","compte","entreprise"})
    private Evaluation evaluation;
    @NotNull
    @JsonIgnoreProperties({"scoreEvaluations","questions"})
    private CategorieQuestion categorieQuestion;
    @NotNull
    private Integer nbPoints;

}
