
package fr.capeb.backend.riskevaluator.model;

import fr.capeb.backend.riskevaluator.model.enumeration.QuestionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "question")
public class QuestionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_question")
    private Integer idQuestion;

    @Enumerated(EnumType.STRING)
    @Column(name = "q_type")
    private QuestionType typeQuestion;

    @NotNull
    @Column(name = "aide_question", columnDefinition = "TEXT")
    private String aideQuestion;

    @NotNull
    @Column(name = "libelle_question",  columnDefinition="TEXT")
    private String libelleQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_categorie", nullable=false)
    private CategorieQuestionEntity categorieQuestion;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL)
    private List<MetierQuestionEntity> metiers = new ArrayList<>();

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReponseEntity> reponses= new ArrayList<ReponseEntity>();


}
