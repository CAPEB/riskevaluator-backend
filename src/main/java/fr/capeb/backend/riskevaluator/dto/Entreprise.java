package fr.capeb.backend.riskevaluator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class Entreprise {

    private Integer noSiret;
    @JsonIgnoreProperties({"scoreCategories","compte","entreprise"})
    private List<Evaluation> evaluations;
    private String nomEntreprise;
    private String effectifEntreprise;
    private Integer anneDeCreation;


}
