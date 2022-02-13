package fr.capeb.backend.riskevaluator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "entreprise")
public class EntrepriseEntity {

    @Id
    @Basic
    @Column(name = "nosiret")
    public Long noSiret;

    @Basic
    @Column(name = "nom_entreprise")
    public String nomEntreprise;


    @Basic
    @Column(name = "effectif")
    public Integer effectifEntreprise;

    @Basic
    @Column(name = "anneedecreation")
    public Integer anneeDeCreation;

    @OneToMany(mappedBy = "entreprise",cascade = CascadeType.ALL)
    private Set<EvaluationEntity> evaluations=new HashSet<>();

}
