package com.example.userordersproject.exceptions;

public class UserNotFoundException extends GenericException  {

    private static final String DEFAULT_MESSAGE = "User not found";
    public UserNotFoundException(String code) {
        super(code + DEFAULT_MESSAGE);
    }
}
