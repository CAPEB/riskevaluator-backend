package fr.capeb.backend.riskevaluator.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class MetierQuestionEntityPK implements Serializable {

    @Column(name = "id_question")
    private Integer questionId;


    @Column(name = "id_metier")
    private Integer metierId;

}
