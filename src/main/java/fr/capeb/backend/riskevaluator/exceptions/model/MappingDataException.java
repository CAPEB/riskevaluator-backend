package fr.capeb.backend.riskevaluator.exceptions.model;


public class MappingDataException extends RuntimeException
{
    static final String error = "Couldn't perform dto mapping";
    public MappingDataException(String message) {
        super(message + error);
    }
    public MappingDataException() {         super(error); }
}