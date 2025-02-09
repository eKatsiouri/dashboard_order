package com.example.userordersproject.exceptions;

public class UserAlreadyExistsException extends GenericException{
    public UserAlreadyExistsException( String message) {
        super( message);
    }
}
