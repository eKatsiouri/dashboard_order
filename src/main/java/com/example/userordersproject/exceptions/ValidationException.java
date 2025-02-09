package com.example.userordersproject.exceptions;

public class ValidationException extends GenericException {
    private static final String DEFAULT_CODE = "Validation Failed";
    public ValidationException(String code,String message) {
        super(message + DEFAULT_CODE);
    }
}
