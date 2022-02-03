package fr.capeb.backend.riskevaluator.exceptions;

import fr.capeb.backend.riskevaluator.exceptions.model.ConflictException;
import fr.capeb.backend.riskevaluator.exceptions.model.CreateOrUpdateException;
import fr.capeb.backend.riskevaluator.exceptions.model.CustomException;
import fr.capeb.backend.riskevaluator.exceptions.model.MappingDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice

@Slf4j
public class GlobalErrorHandler
    {
        @ExceptionHandler(CreateOrUpdateException.class)
        public ResponseEntity<Object> createOrUpdateQuestionnaire(ConflictException ex, WebRequest request) {

            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", "Something goes wrong while creating or update resource ");

            return new ResponseEntity<>(body, INTERNAL_SERVER_ERROR);
        }


        @ExceptionHandler(ConflictException.class)
        public ResponseEntity<Object> handleCityNotFoundException(
                ConflictException ex, WebRequest request) {

            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", "Data you are trying to update or insert, resulted in conflict due to unique contraints  ");

            return new ResponseEntity<>(body,CONFLICT);
        }


        @ExceptionHandler(MappingDataException.class)
        public ResponseEntity handleDataMappingException(
                MappingDataException ex, WebRequest request) {

            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", "Error while performing dto/database entity mapping  ");

            return new ResponseEntity(body, INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(CustomException.class)
        public ResponseEntity handleCustomException(
                CustomException ex, WebRequest request) {

            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", ex.getMessage());

            return new ResponseEntity(body, INTERNAL_SERVER_ERROR);
        }

      /*  @ExceptionHandler({SQLIntegrityConstraintViolationException.class,
                            DataIntegrityViolationException.class,
                            ConstraintViolationException.class
        })
        public ResponseEntity<Object> handleSqlConflictException(
                ConflictException ex, WebRequest request) {

            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", "Data you are trying to update or insert, violate database integrity  ");

            return new ResponseEntity<>(body,CONFLICT);
        }
        */

    }
