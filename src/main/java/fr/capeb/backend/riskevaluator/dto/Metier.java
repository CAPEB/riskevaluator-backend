package fr.capeb.backend.riskevaluator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Metier {
    @EqualsAndHashCode.Include
    private Integer idMetier;
    @NotNull
    @EqualsAndHashCode.Exclude
    private String nomMetier;

    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"categorieQuestion","reponses","metiers"})
    private List<Question> questions=new ArrayList<>();
}
