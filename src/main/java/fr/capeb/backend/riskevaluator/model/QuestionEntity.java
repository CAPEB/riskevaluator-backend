
package fr.capeb.backend.riskevaluator.model;

import fr.capeb.backend.riskevaluator.model.enumeration.QuestionType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "question")
public class QuestionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_question")
    private Integer idQuestion;

    @Column(name = "id_categorie")
    private Integer idCategorie;

    @Enumerated(EnumType.STRING)
    @Column(name = "q_type")
    private QuestionType qType;

    @Column(name = "aide")
    private String aide;

    @Column(name = "libelle_question")
    private String libelleQuestion;


}
