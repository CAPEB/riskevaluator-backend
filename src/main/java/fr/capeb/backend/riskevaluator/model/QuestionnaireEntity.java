package fr.capeb.backend.riskevaluator.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
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
}
