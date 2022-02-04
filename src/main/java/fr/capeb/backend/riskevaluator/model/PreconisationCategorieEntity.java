package fr.capeb.backend.riskevaluator.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "preconisation_categorie")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PreconisationCategorieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_preconisation")
    private Integer idPreconisation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_categorie", nullable=false)
    private CategorieQuestionEntity idCategorie;


    @Column(name = "contenu",  columnDefinition="TEXT")
    private String contenu;

    @Column(name = "viewIfPourcentageScoreLessThan")
    private Integer viewIfPourcentageScoreLessThan;
}
