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

    @Column(name = "id_categorie")
    private Integer idCategorie;

    @Column(name = "scope")
    private String scope;

    @Column(name = "score_min_glob")
    private Integer scoreMinGlob;

    @Column(name = "score_max_glob")
    private Integer scoreMaxGlob;
}
