package com.mycompany.empms.service;

import com.mycompany.empms.dto.AddEmployeeRequest;
import com.mycompany.empms.dto.EmployeeDetails;
import com.mycompany.empms.dto.UpdateEmployeeRequest;
import com.mycompany.empms.entity.Employee;
import com.mycompany.empms.exceptions.EmployeeNotFoundException;
import com.mycompany.empms.exceptions.InvalidEmployeeAgeException;
import com.mycompany.empms.exceptions.InvalidEmployeeIdException;
import com.mycompany.empms.exceptions.InvalidEmployeeNameException;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
public interface IEmployeeService {

    EmployeeDetails add(@Valid AddEmployeeRequest requestData) throws InvalidEmployeeNameException, InvalidEmployeeAgeException;

    EmployeeDetails update(@Valid UpdateEmployeeRequest requestData)
            throws InvalidEmployeeNameException, InvalidEmployeeAgeException, EmployeeNotFoundException, InvalidEmployeeIdException;


    EmployeeDetails findDetailsById(@Min(1) long id) throws InvalidEmployeeIdException, EmployeeNotFoundException;
    List<EmployeeDetails> findAll();

}
