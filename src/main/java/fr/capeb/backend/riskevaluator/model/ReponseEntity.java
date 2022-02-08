package fr.capeb.backend.riskevaluator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "reponse")
@Getter
@Setter
@NoArgsConstructor
public class ReponseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reponse")
    private Integer idReponse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_question", nullable=false)
    private QuestionEntity question;


    @Column(name = "nb_points")
    private Integer nbPoints;

    @Column(name = "contenu", columnDefinition="TEXT")
    private String contenu;

}
