package fr.capeb.backend.riskevaluator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import fr.capeb.backend.riskevaluator.dto.CategorieQuestion;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "questionnaire", uniqueConstraints = {
        @UniqueConstraint(columnNames = "thematique")
})
public class QuestionnaireEntity {

    @Id
    @Column(name = "id_questionnaire")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idQuestionnaire;


    @Column(name = "thematique", nullable=false)
    private String thematique;

    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL)
    private List<CategorieQuestionEntity> categorieQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL)
    private List<PreconisationGlobaleEntity> preconisationGlobales = new ArrayList<>();

}
