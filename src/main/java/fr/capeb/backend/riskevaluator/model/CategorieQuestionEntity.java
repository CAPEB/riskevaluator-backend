package fr.capeb.backend.riskevaluator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public QuestionnaireEntity questionnaire;

    @Column(name = "libelle", nullable=false)
    public String libelle;

    @OneToMany(mappedBy = "categorieQuestion",cascade = CascadeType.ALL)
    private Set<ScoreCategoryEntity> scoreEvaluations=new HashSet<>();

    @OneToMany(mappedBy = "categorieQuestion",cascade = CascadeType.ALL)
    private Set<PreconisationCategorieEntity> preconisationsCategorie= new HashSet<>();

    @OneToMany(mappedBy = "categorieQuestion", cascade = CascadeType.ALL)
    private Set<QuestionEntity> questions= new HashSet<>();


}
