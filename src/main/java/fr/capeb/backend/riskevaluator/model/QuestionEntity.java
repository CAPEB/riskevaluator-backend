
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
import java.util.ArrayList;
import java.util.List;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_categorie", nullable=false)
    private CategorieQuestionEntity idCategorie;


    @Enumerated(EnumType.STRING)
    @Column(name = "q_type")
    private QuestionType qType;

    @Column(name = "aide")
    private String aide;

    @Column(name = "libelle_question",  columnDefinition="TEXT")
    private String libelleQuestion;

    @OneToMany(mappedBy = "idQuestion",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MetierQuestionEntity> metierQuestions= new ArrayList<MetierQuestionEntity>();

    @OneToMany(mappedBy = "idQuestion",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReponseEntity> reponses= new ArrayList<ReponseEntity>();


}
