package fr.capeb.backend.riskevaluator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "metier_question")
@IdClass(MetierQuestionEntityPK.class)
public class MetierQuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_question")
    private Integer idQuestion;

    @Id
    @Column(name = "id_metier")
    private Integer idMetier;
}
