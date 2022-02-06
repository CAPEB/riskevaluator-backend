package fr.capeb.backend.riskevaluator.dto;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Metier {
    private Integer idMetier;
    @NotNull
    private String nomMetier;
    private List<Question> questions;
}
