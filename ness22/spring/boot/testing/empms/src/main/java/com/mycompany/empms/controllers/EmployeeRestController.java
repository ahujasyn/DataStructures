package com.mycompany.empms.controllers;

import com.mycompany.empms.dto.AddEmployeeRequest;
import com.mycompany.empms.dto.EmployeeDetails;
import com.mycompany.empms.dto.UpdateEmployeeRequest;
import com.mycompany.empms.entity.Employee;
import com.mycompany.empms.exceptions.EmployeeNotFoundException;
import com.mycompany.empms.exceptions.InvalidEmployeeAgeException;
import com.mycompany.empms.exceptions.InvalidEmployeeIdException;
import com.mycompany.empms.exceptions.InvalidEmployeeNameException;
import com.mycompany.empms.service.IEmployeeService;
import com.mycompany.empms.util.EmployeeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Validated
@RequestMapping("/employees")
@RestController
public class EmployeeRestController {

    private static final Logger Log= LoggerFactory.getLogger(EmployeeRestController.class);

    @Autowired
    private IEmployeeService service;



    //@RequestMapping(value="/byid/{id}", method=RequestMethod.GET)
    @GetMapping("/byid/{id}")
    public EmployeeDetails fetchById(@PathVariable @Min(1) long id) throws Exception {
      Log.info("inside fetchById() id="+id);
      EmployeeDetails response=service.findEmployeeDetailsById(id);
      return response;
    }

    /*
    Another way where we are returning responsesentity,many variants for ResponseEntity constructors  are there
     based on requirement, one of them can be used

    @GetMapping("/byid/{id}")
    public ResponseEntity<EmployeeDetails> fetchById(@PathVariable @Min(1) long id) throws Exception {
        Log.info("inside fetchById() id="+id);
        EmployeeDetails response=service.findEmployeeDetailsById(id);
        ResponseEntity<EmployeeDetails>responseEntity=new ResponseEntity<>(response,HttpStatus.OK);
        return responseEntity;
    }
  */

    @GetMapping("/bydeptid/{deptId}")
    public List<EmployeeDetails>findEmployeesByDepartmentId(@PathVariable @Min(1) long deptId) throws Exception{
      return  service.findEmployeeDetailsByDepartmentId(deptId);
    }


    @GetMapping("/bydeptname/{deptName}")
    public List<EmployeeDetails>findEmployeesByDepartmentName(@PathVariable @NotBlank @Size(min = 2,max=10)  String deptName){
       return service.findEmployeeDetailsByDepartmentName(deptName);
    }

    @GetMapping("/all")
    public List<EmployeeDetails> fetchAll(){
        List<EmployeeDetails>list=service.findAllEmployeeDetails();
        return list;
    }

    @GetMapping("/all/paging/{pageNumber}/{blockSize}")
    public List<EmployeeDetails> fetchAllByPaging(@PathVariable @Min(0) int pageNumber, @Min(1) @PathVariable() int blockSize){
        List<EmployeeDetails>list=service.findAllEmployeesDetail( pageNumber, blockSize);
        return list;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    //@RequestMapping(value = "/add",method= RequestMethod.POST)
    public EmployeeDetails add(@RequestBody @NotNull @Valid AddEmployeeRequest  requestData) throws Exception{
       EmployeeDetails response =service.add(requestData);
       return response;
    }

    //@RequestMapping(value = "/add",method= RequestMethod.PUT)
    @PutMapping("/update")
    public EmployeeDetails update(@RequestBody @Valid  UpdateEmployeeRequest requestData) throws Exception{
        EmployeeDetails response=service.updateEmployeeDetails(requestData);
        return response;
    }



}
