package fr.capeb.backend.riskevaluator.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "reponse")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ReponseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reponse")
    @EqualsAndHashCode.Include
    private Integer idReponse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_question", nullable=false)
    @EqualsAndHashCode.Exclude
    private QuestionEntity question;

    @NotNull
    @Column(name = "nb_points")
    @EqualsAndHashCode.Exclude
    private Integer nbPoints;

    @NotNull
    @Column(name = "contenu", columnDefinition="TEXT")
    @EqualsAndHashCode.Exclude
    private String contenu;

}
