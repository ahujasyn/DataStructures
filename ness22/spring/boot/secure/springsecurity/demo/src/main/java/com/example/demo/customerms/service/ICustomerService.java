package com.example.demo.customerms.service;

import com.example.demo.customerms.dto.CustomerDetails;
import com.example.demo.customerms.dto.RegisterCustomerRequest;
import com.example.demo.exceptions.UserNotFoundException;

import java.util.List;

public interface ICustomerService {

    CustomerDetails register(RegisterCustomerRequest request);

    CustomerDetails findByUsername(String username)throws UserNotFoundException;

    List<CustomerDetails> findAll();

}
