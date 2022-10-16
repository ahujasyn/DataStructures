package com.mycompany.empms.exceptions;

public class EmployeeNotFoundException extends Throwable {

    public EmployeeNotFoundException(String msg) {
        super(msg);
    }

    public EmployeeNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }


}
