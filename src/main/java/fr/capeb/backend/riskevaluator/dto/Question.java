package fr.capeb.backend.riskevaluator.dto;


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
    private CategorieQuestion categorieQuestion;
    private QuestionType qType;
    private String  aide;
    @NotNull
    private String  libelleQuestion;
    private List<Reponse> reponses;

    private List<Metier> metiers;
}
