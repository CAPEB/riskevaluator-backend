package fr.capeb.backend.riskevaluator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "categorie_question")

public class CategorieQuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categorie")
    public Integer idCategorie;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_questionnaire", nullable=false)
    public QuestionnaireEntity idQuestionnaire;

    @Column(name = "libelle", nullable=false)
    public String libelle;

    @OneToMany(mappedBy = "idCategorie")
    private List<ScoreCategoryEntity> scoreCategories;

}
