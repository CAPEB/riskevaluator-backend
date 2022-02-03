package fr.capeb.backend.riskevaluator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import fr.capeb.backend.riskevaluator.model.enumeration.QuestionType;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {

    private Integer idQuestion;
    private Integer idCategorie;
    private QuestionType qType;
    private String  aide;
    private String  libelleQuestion;

}
