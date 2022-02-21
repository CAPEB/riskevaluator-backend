
package fr.capeb.backend.riskevaluator.model;

import fr.capeb.backend.riskevaluator.model.enumeration.QuestionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

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

    @Column(name = "score_max_possible", columnDefinition = "integer default 0")
    private Integer scoreMaxPossibleQuestion;

    @NotNull
    @Column(name = "libelle_question",  columnDefinition="TEXT")
    private String libelleQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_categorie", nullable=false)
    private CategorieQuestionEntity categorieQuestion;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MetierQuestionEntity> metiers = new LinkedHashSet<>();

    @OrderBy("contenu")
    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ReponseEntity> reponses= new HashSet<>();


}
