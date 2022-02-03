package fr.capeb.backend.riskevaluator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionMsg {
    ID_NOT_FOUND("The id was not found, make sure your It's id existe in database");
    public String value;

}
