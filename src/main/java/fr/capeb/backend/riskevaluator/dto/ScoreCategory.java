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
    @JsonIgnoreProperties({"scoreCategories","compte"})
    private Evaluation evaluation;
    @NotNull
    @JsonIgnoreProperties({"questionnaire","scoreEvaluations","questions","preconisationsCategorie"})
    private CategorieQuestion categorieQuestion;
    @NotNull
    private Integer nbPoints;

}
