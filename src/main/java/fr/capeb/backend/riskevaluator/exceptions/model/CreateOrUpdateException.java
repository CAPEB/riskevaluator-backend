package fr.capeb.backend.riskevaluator.exceptions.model;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CreateOrUpdateException extends RuntimeException
{
    static final String error = "Couldn't perform create or update operation";

    public CreateOrUpdateException(String message) {
        super(message + error);
    }
    public CreateOrUpdateException() {
        super(error);
    }
}