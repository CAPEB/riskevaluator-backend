package fr.capeb.backend.riskevaluator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Entreprise {

    private Long noSiret;
    private String nomEntreprise;
    private Integer effectifEntreprise;
    private Integer anneeDeCreation;
    @JsonIgnoreProperties({"scoreCategories","compte","entreprise"})
    private List<Evaluation> evaluations=new ArrayList<>();

}
