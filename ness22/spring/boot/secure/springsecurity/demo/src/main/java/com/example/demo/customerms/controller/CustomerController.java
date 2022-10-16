package com.example.demo.customerms.controller;

import com.example.demo.customerms.dto.CustomerDetails;
import com.example.demo.customerms.dto.RegisterCustomerRequest;
import com.example.demo.customerms.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/p/customers/add")
    public CustomerDetails register(@RequestBody RegisterCustomerRequest request){
        return customerService.register(request);
    }

    @GetMapping("/c/customers/byname/{username}")
    public CustomerDetails findByName(@PathVariable String username) throws Exception{
        return customerService.findByUsername(username);
    }

    @GetMapping("/a/customers/all")
    public List<CustomerDetails> findAll() {
        return customerService.findAll();
    }

}
