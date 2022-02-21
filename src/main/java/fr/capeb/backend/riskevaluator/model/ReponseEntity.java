package fr.capeb.backend.riskevaluator.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "reponse")
@Getter
@Setter
@NoArgsConstructor
public class ReponseEntity {
    @SortNatural
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reponse")
    private Integer idReponse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_question", nullable=false)
    private QuestionEntity question;

    @NotNull
    @Column(name = "nb_points")
    private Integer nbPoints;

    @NotNull
    @Column(name = "contenu", columnDefinition="TEXT")
    private String contenu;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReponseEntity that = (ReponseEntity) o;
        if(that.getIdReponse()==null ||that.getIdReponse()==0||idReponse==0||idReponse==null) return false;
        return idReponse.equals(that.idReponse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReponse);
    }


}
