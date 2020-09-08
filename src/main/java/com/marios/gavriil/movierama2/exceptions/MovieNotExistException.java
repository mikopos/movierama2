package com.marios.gavriil.movierama2.exceptions;

public class MovieNotExistException extends Exception{
    public MovieNotExistException(String errorMessage){
        super(errorMessage);
    }
}
