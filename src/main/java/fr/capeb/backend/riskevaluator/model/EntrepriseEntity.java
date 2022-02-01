package fr.capeb.backend.riskevaluator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "entreprise")
public class EntrepriseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noentreprise")
    public Integer noEntreprise;

    @Basic
    @Column(name = "nosiret")
    public String noSiret;

    @Basic
    @Column(name = "nom_entreprise")
    public String nomEntreprise;


    @Basic
    @Column(name = "effectif")
    public Integer effectif;

    @Basic
    @Column(name = "anneedecreation")
    public Integer AnneeDeCreation;

}
