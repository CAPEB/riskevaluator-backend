package fr.capeb.backend.riskevaluator.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategorieQuestion {
    public Integer idCategorie;
    @NotNull
    public String libelle;
    @NotNull
    @JsonIgnoreProperties({"categorieQuestions"} )
    public Questionnaire questionnaire;
    @JsonIgnoreProperties({"categorieQuestion","evaluation"})
    public List<ScoreCategory> scoreEvaluations= new ArrayList<>();
    @JsonIgnoreProperties({"categorieQuestion","metiers"})
    public List<Question> questions = new ArrayList<>();
    @JsonIgnoreProperties("categorieQuestion")
    public List<PreconisationCategorie> preconisationsCategorie= new ArrayList<>();

}
