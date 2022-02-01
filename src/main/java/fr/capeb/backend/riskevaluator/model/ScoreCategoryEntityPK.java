package fr.capeb.backend.riskevaluator.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ScoreCategoryEntityPK implements Serializable {
    @Column(name = "id_evaluation")
    @Id
    private Integer idEvaluation;

    @Column(name = "id_categorie")
    @Id
    private Integer idCategorie;
}
