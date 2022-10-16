package com.mycompany.empms.service;

import com.mycompany.empms.dto.AddEmployeeRequest;
import com.mycompany.empms.dto.EmployeeDetails;
import com.mycompany.empms.dto.UpdateEmployeeRequest;
import com.mycompany.empms.entity.Department;
import com.mycompany.empms.entity.Employee;
import com.mycompany.empms.exceptions.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Validated
public interface IEmployeeService {

    EmployeeDetails add(@NotNull @Valid AddEmployeeRequest request) throws  DepartmentNotFoundException;

    Employee update(@NotNull @Valid Employee employee)
            throws EmployeeNotFoundException, InvalidEmployeeIdException;

    EmployeeDetails updateEmployeeDetails(@NotNull @Valid UpdateEmployeeRequest requestData)
            throws  EmployeeNotFoundException, InvalidEmployeeIdException;


    Employee findById(@Min(1)long id) throws InvalidEmployeeIdException, EmployeeNotFoundException;

    EmployeeDetails findEmployeeDetailsById(@Min(1)long id) throws  EmployeeNotFoundException;


    List<Employee> findAll();


    List<Employee> findAll(@Min(0)int pageNumber,@Min(1) int blockSize);

    List<EmployeeDetails> findAllEmployeeDetails();

    List<EmployeeDetails> findAllEmployeesDetail(@Min(0)int pageNumber,@Min(1) int blockSize);

    List<Employee> fetchByName(@Size(min=2,max=12) String name);

    List<Employee> fetchByNameAndAge(@Size(min=2,max=12) String name,@Min(18)@Max(60) int age);

    List<Employee>fetchByAgeGreaterThan(@Min(18)@Max(60)  int age);

    List<EmployeeDetails>findEmployeeDetailsByDepartmentId(@Min(1) long departmentId) throws DepartmentNotFoundException;


    List<EmployeeDetails>findEmployeeDetailsByDepartmentName(@Size(min = 2, max=10) String departmentName);
}
