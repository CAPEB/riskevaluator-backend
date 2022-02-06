package fr.capeb.backend.riskevaluator.dto;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Questionnaire {

    private Integer idQuestionnaire;
    @NotNull
    private String thematique;
    private List<PreconisationGlobale> preconisationGlobales;
    private List<CategorieQuestion> categorieQuestions;


}
