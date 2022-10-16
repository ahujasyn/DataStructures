package com.mycompany.empms.controllers;

import com.mycompany.empms.dto.AddEmployeeRequest;
import com.mycompany.empms.dto.EmployeeDetails;
import com.mycompany.empms.exceptions.EmployeeNotFoundException;
import com.mycompany.empms.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;



@RequestMapping("/employees")
@RestController
public class EmployeeController {

    @Autowired
    private IEmployeeService service;

    @PostMapping("/add")
    public EmployeeDetails add(@RequestBody AddEmployeeRequest request) throws  Exception{
        EmployeeDetails response=service.add(request);
        return response;
    }

    @GetMapping("/byid/{id}")
    public EmployeeDetails findEmployeeById(@PathVariable long id) throws  Exception{
       EmployeeDetails response =service.findDetailsById(id);
       return response;

    }


}
