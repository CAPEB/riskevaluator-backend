package fr.capeb.backend.riskevaluator.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.capeb.backend.riskevaluator.model.enumeration.QuestionType;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    private Integer idQuestion;
    @NotNull
    @JsonIgnoreProperties({"questionnaire","scoreEvaluations","questions","preconisationsCategorie"})
    private CategorieQuestion categorieQuestion;

    @NotNull
    private QuestionType typeQUestion;

    private String  aide;
    @NotNull
    private String  libelleQuestion;

    @JsonIgnoreProperties("question")
    private List<Reponse> reponses;
    @JsonIgnoreProperties("questions")
    private List<Metier> metiers;
}
