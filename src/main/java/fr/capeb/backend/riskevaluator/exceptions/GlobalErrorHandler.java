package fr.capeb.backend.riskevaluator.exceptions;

import fr.capeb.backend.riskevaluator.exceptions.model.CreateOrUpdateException;
import fr.capeb.backend.riskevaluator.exceptions.model.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.InvalidParameterException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
public class GlobalErrorHandler
{
    @ResponseBody
    @ExceptionHandler(CreateOrUpdateException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    String createOrUpdateQuestionnaire(Exception ex) {
        log.error("An unexpected controller error has happened", ex);
        return ex.getMessage();
    }
}
