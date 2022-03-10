package fr.capeb.backend.riskevaluator.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_questionnaire", nullable=false)
    private QuestionnaireEntity questionnaire;

    @Column(name = "contenu", columnDefinition="TEXT")
    private String contenu;
    @NotNull
    @Column(name = "viewifpourcentagescorelessthan")
    private Integer viewIfPourcentageScoreLessThan;

}
