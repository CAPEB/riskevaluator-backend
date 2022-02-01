package fr.capeb.backend.riskevaluator.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class MetierQuestionEntityPK implements Serializable {

    @Column(name = "id_question")
    @Id
    private Integer idQuestion;


    @Column(name = "id_metier")
    @Id
    private Integer idMetier;

}
