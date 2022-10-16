package com.mycompany.empms.service;

import com.mycompany.empms.entity.Employee;
import com.mycompany.empms.exceptions.EmployeeNotFoundException;
import com.mycompany.empms.exceptions.InvalidEmployeeAgeException;
import com.mycompany.empms.exceptions.InvalidEmployeeIdException;
import com.mycompany.empms.exceptions.InvalidEmployeeNameException;

import java.util.List;

public interface IEmployeeService {

    Employee add(String name, int age) throws InvalidEmployeeNameException, InvalidEmployeeAgeException;

    Employee update(Employee employee)
            throws InvalidEmployeeNameException, InvalidEmployeeAgeException, EmployeeNotFoundException, InvalidEmployeeIdException;

    Employee findById(long id) throws InvalidEmployeeIdException, EmployeeNotFoundException;

    List<Employee> findAll();

}
