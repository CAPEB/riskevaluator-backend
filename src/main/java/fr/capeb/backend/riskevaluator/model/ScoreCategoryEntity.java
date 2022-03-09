package fr.capeb.backend.riskevaluator.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "score_category")
public class ScoreCategoryEntity {
    @EqualsAndHashCode.Include
    @EmbeddedId
    private ScoreCategoryEntityPK key = new ScoreCategoryEntityPK();

    @EqualsAndHashCode.Exclude
    @MapsId(value = "idEvaluation")
    @ManyToOne
    @JoinColumn(name = "id_evaluation", referencedColumnName = "id_evaluation")
    private EvaluationEntity evaluation;

    @EqualsAndHashCode.Exclude
    @MapsId(value = "idCategorie")
    @ManyToOne
    @JoinColumn(name = "id_categorie", referencedColumnName = "id_categorie")
    private CategorieQuestionEntity categorieQuestion;

    @EqualsAndHashCode.Exclude
    @Column(name = "nb_points")
    private Integer nbPoints;

}
