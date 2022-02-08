package fr.capeb.backend.riskevaluator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Questionnaire {

    private Integer idQuestionnaire;
    @NotNull
    private String thematique;
    @JsonIgnoreProperties("questionnaire")
    private List<PreconisationGlobale> preconisationGlobales=new ArrayList<PreconisationGlobale>();
    @JsonIgnoreProperties({"questionnaire","scoreEvaluations","questions","preconisations"})
    private List<CategorieQuestion> categorieQuestions=new ArrayList<CategorieQuestion>();


}
