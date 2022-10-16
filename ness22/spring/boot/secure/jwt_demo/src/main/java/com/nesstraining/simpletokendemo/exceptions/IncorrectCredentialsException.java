package com.nesstraining.simpletokendemo.exceptions;

public class IncorrectCredentialsException extends Exception{
    public IncorrectCredentialsException(String msg){
        super(msg);
    }
}
