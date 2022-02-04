package fr.capeb.backend.riskevaluator.exceptions;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionMsg {
    ID_NOT_FOUND("the id was not found, make sure your It's ID existe in database");
    public String value;

}
