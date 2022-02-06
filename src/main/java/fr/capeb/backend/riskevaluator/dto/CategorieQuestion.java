package fr.capeb.backend.riskevaluator.dto;


import lombok.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CategorieQuestion {
    public Integer idCategorie;
    @NotNull
    public String libelle;
    @NotNull
    public Questionnaire questionnaire;
    public List<ScoreCategory> scoreEvaluations;
    public List<Question> questions;
    public List<PreconisationCategorie> preconisations;

}
