package com.example.userordersproject.exceptions;

public class ProductNotFoundException extends GenericException{
    private static final String DEFAULT_CODE = "NotFound";

    public ProductNotFoundException( String message) {
        super( message + DEFAULT_CODE );
    }
}
