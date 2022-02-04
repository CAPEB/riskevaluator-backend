package fr.capeb.backend.riskevaluator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "evaluation")
public class EvaluationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluation")
    private Integer idEvaluation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_compte", nullable=false)
    private CompteEntity idCompte;

    @OneToMany(mappedBy = "idEvaluation", fetch = FetchType.LAZY)
    private List<ScoreCategoryEntity> scoreCategories;


}
