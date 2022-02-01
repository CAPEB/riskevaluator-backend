package fr.capeb.backend.riskevaluator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "questionnaire")
public class QuestionnaireEntity {

    @Id
    @Column(name = "id_questionnaire")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idQuestionnaire;


    @Column(name = "thematique")
    private String thematique;
}
