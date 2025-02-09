package com.example.userordersproject.exceptions;

public class OrderNotFoundException extends GenericException {
    private static final String DEFAULT_CODE = "NotFound";

    public OrderNotFoundException(String message) {
        super( message + DEFAULT_CODE );
    }
}
