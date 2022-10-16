package com.mycompany.empms.exceptions;

public class InvalidEmployeeNameException extends Exception{

    public InvalidEmployeeNameException(String msg){
        super(msg);
    }
}
