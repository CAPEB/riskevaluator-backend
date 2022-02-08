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
@Table(name = "metier")
public class MetierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_metier")
    private Integer idMetier;

    @Column(name = "nom_metier",nullable=false)
    private String nomMetier;

    @OneToMany(mappedBy = "idMetier",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<MetierQuestionEntity> questionsMetier;
}
