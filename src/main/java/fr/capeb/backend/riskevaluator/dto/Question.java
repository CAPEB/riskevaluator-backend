package fr.capeb.backend.riskevaluator.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.capeb.backend.riskevaluator.model.enumeration.QuestionType;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Question {

    private Integer idQuestion;
    @NotNull
    private QuestionType typeQuestion;
    @NotNull
    private String  libelleQuestion;

    private Integer scoreMaxPossibleQuestion;
    @NotNull
    @JsonIgnoreProperties({"questionnaire","scoreEvaluations","questions","preconisationsCategorie"})
    private CategorieQuestion categorieQuestion;
    @JsonIgnoreProperties("question")
    private List<Reponse> reponses=new ArrayList<>();
    @NotNull
    @JsonIgnoreProperties("questions")
    private List<Metier> metiers=new ArrayList<>();
}
