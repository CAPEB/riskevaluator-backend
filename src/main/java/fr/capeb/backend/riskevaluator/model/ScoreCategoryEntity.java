package fr.capeb.backend.riskevaluator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "score_category")
@IdClass(ScoreCategoryEntityPK.class)
public class ScoreCategoryEntity {

    @Id
    @Column(name = "id_evaluation")
    private Integer idEvaluation;

    @Id
    @Column(name = "id_categorie")
    private Integer idCategorie;

    @Column(name = "nb_points")

    private Integer nbPoints;

}
