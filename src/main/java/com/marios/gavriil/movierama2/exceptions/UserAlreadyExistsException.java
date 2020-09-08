package com.marios.gavriil.movierama2.exceptions;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String errorMessage){
        super(errorMessage);
    }
}
