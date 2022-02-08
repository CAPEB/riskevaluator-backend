package fr.capeb.backend.riskevaluator.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PreconisationGlobale {
    private Integer idPreconisationG;
    @NotNull
    @JsonIgnoreProperties("preconisationGlobales")
    private Questionnaire questionnaire;
    @NotNull
    private String contenu;
    @NotNull
    private Integer viewIfPourcentageScoreLessThan;
}
