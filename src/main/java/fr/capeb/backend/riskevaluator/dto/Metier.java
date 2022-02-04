package fr.capeb.backend.riskevaluator.dto;

import lombok.*;

import javax.persistence.Column;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Metier {
    private Integer idMetier;
    private String nomMetier;
}
