package fr.capeb.backend.riskevaluator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import fr.capeb.backend.riskevaluator.model.enumeration.QuestionType;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Question {

    private Integer idQuestion;
    @NotNull
    private Integer idCategorie;
    private QuestionType qType;
    private String  aide;
    private String  libelleQuestion;

}
