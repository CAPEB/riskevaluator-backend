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
public class MetierQuestionEntity {

    @EmbeddedId
    private MetierQuestionEntityPK key = new MetierQuestionEntityPK();

    @MapsId(value = "questionId")
    @ManyToOne
    @JoinColumn(name = "id_question", referencedColumnName = "id_question")
    private QuestionEntity question;


    @MapsId(value = "metierId")
    @ManyToOne
    @JoinColumn(name = "id_metier", referencedColumnName = "id_metier")
    private MetierEntity metier;

}
