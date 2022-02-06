package fr.capeb.backend.riskevaluator.model;

import fr.capeb.backend.riskevaluator.dto.CategorieQuestion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "score_category")
public class ScoreCategoryEntity {

    @EmbeddedId
    private ScoreCategoryEntityPK key;


    @MapsId(value = "idEvaluation")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evaluation", referencedColumnName = "id_evaluation")
    private EvaluationEntity idEvaluation;


    @MapsId(value = "idCategorie")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorie", referencedColumnName = "id_categorie")
    private CategorieQuestionEntity idCategorie;

    @Column(name = "nb_points")
    private Integer nbPoints;

}
