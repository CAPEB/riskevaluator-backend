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
public class PreconisationCategorie {
    private Integer idPreconisation;
    @NotNull
    private Integer idCategorie;
    private String scope;
    private Integer scoreMinGlob;
    private Integer scoreMaxGlob;

}
