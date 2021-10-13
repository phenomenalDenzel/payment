package com.qds.ulinzi.service.exceptions;

public class MonnifyException extends RuntimeException{
    public MonnifyException(){
        super("MONNIFY_EXCEPTION");
    }

    public MonnifyException(String message){
        super(message);
    }
}
