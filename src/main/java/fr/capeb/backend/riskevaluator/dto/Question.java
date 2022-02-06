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
@EqualsAndHashCode
public class Question {

    private Integer idQuestion;
    @NotNull
    private CategorieQuestion categorieQuestion;
    @NotNull
    private QuestionType qType;
    private String  aide;
    @NotNull
    private String  libelleQuestion;
    private List<Reponse> reponses;
    @NotNull
    private List<Metier> metiers;
}
