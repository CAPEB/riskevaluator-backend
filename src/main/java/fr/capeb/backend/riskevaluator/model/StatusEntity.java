package fr.capeb.backend.riskevaluator.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @Column(name = "ok")
    private  String ok ;

}
