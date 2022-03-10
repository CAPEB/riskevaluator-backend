package fr.capeb.backend.riskevaluator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Reponse {
    private Integer idReponse;
    @NotNull
    @JsonIgnoreProperties({"categorieQuestion","reponses","metiers"})
    private Question question;
    @NotNull
    private Integer nbPoints;
    @NotNull
    private String contenu;
}
