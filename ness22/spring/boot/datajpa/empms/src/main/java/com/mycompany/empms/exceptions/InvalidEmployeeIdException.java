package com.mycompany.empms.exceptions;

public class InvalidEmployeeIdException extends Exception{
    public InvalidEmployeeIdException(String msg){
        super(msg);
    }
}
