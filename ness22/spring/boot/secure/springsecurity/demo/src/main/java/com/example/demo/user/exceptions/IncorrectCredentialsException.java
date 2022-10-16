package com.example.demo.user.exceptions;

public class IncorrectCredentialsException extends Exception{
    public IncorrectCredentialsException(String msg){
        super(msg);
    }
}
