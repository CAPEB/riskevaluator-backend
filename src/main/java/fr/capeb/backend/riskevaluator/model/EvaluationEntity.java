package fr.capeb.backend.riskevaluator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name = "score_generale")
    private Integer scoreGeneraleEvaluation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_compte", nullable=false)
    private CompteEntity compte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nosiret", nullable=false)
    private EntrepriseEntity entreprise;
    @OrderBy("categorieQuestion.libelle")
    @OneToMany(mappedBy = "evaluation",cascade = CascadeType.ALL)
    private Set<ScoreCategoryEntity> scoreCategories=new HashSet<>();





}
