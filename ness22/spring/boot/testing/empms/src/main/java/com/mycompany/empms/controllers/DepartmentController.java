package com.mycompany.empms.controllers;

import com.mycompany.empms.dto.AddDepartmentRequest;
import com.mycompany.empms.dto.DepartmentDetails;
import com.mycompany.empms.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/departments")
@RestController
public class DepartmentController {

    @Autowired
    private IDepartmentService service;

    @PostMapping("/add")
    public DepartmentDetails add(@RequestBody  AddDepartmentRequest request){
        return service.add(request);
    }

}
