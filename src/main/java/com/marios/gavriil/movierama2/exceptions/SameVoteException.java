package com.marios.gavriil.movierama2.exceptions;

public class SameVoteException extends  Exception{

    public SameVoteException(String errorMessage){
        super(errorMessage);
    }
}
