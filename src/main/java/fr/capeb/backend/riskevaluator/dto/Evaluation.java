package fr.capeb.backend.riskevaluator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Evaluation {

    private Integer idEvaluation;
    private Integer ScoreGeneraleEvaluation;
    @JsonIgnoreProperties({"evaluations"})
    private Compte compte;
    @NotNull
    @JsonIgnoreProperties({"evaluation","categorieQuestion"})
    private List<ScoreCategory> scoreCategories;
    @NotNull
    @JsonIgnoreProperties({"evaluations"})
    private Entreprise entreprise;




}
