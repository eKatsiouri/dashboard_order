package com.example.userordersproject.exceptions;

public class NotAuthorizedException extends GenericException{
    private static final String DEFAULT_CODE = "Not authorized";
    public NotAuthorizedException( String message) {
        super( message + DEFAULT_CODE  );
    }
}
