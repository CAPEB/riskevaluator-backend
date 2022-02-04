package fr.capeb.backend.riskevaluator.dto;

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
    private Integer idQuestionnaire;
    private String contenu;
    private Integer scoreMinGlob;
    private Integer scoreMaxGlob;
}