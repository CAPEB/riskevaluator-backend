package fr.capeb.backend.riskevaluator.dto;

import fr.capeb.backend.riskevaluator.model.CategorieQuestionEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    private String contenu;
    private Integer viewIfPourcentageScoreLessThan;


}
