package fr.capeb.backend.riskevaluator.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ScoreCategoryEntityPK implements Serializable {
    @Column(name = "id_evaluation")
    private Integer idEvaluation;

    @Column(name = "id_categorie")
    private Integer idCategorie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreCategoryEntityPK that = (ScoreCategoryEntityPK) o;
        return idEvaluation.equals(that.idEvaluation) && idCategorie.equals(that.idCategorie);
    }

}
