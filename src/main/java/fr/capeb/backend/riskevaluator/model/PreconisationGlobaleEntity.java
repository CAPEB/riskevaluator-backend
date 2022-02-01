package fr.capeb.backend.riskevaluator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "preconisation_globale")
public class PreconisationGlobaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_preconisation_g")
    private Integer idPreconisationG;

    @Column(name = "id_questionnaire")
    private Integer idQuestionnaire;

    @Column(name = "contenu")
    private String contenu;

    @Column(name = "score_min_glob")
    private Integer scoreMinGlob;

    @Column(name = "score_max_glob")
    private Integer scoreMaxGlob;
}
