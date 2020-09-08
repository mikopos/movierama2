package com.marios.gavriil.movierama2.exceptions;

public class SameVoterAndCreatorException extends Exception{
    public SameVoterAndCreatorException(String errorMessage){
        super(errorMessage);
    }
}
