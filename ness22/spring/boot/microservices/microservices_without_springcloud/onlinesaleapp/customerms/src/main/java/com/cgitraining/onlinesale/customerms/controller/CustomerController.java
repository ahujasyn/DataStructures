package com.cgitraining.onlinesale.customerms.controller;

import com.cgitraining.onlinesale.customerms.dto.BuyProductRequest;
import com.cgitraining.onlinesale.customerms.dto.CustomerDetails;
import com.cgitraining.onlinesale.customerms.dto.RegisterCustomer;
import com.cgitraining.onlinesale.customerms.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customers")
@RestController
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/add")
    public CustomerDetails register(@RequestBody RegisterCustomer requestData){
       CustomerDetails response= customerService.register(requestData);
       return response;
    }

    @GetMapping("/byid/{id}")
    public CustomerDetails findById(@PathVariable long id) throws Exception{
        CustomerDetails response=customerService.findCustomerDetailsById(id);
        return response;
    }

    @PutMapping("/buy")
    public CustomerDetails buyProduct(@RequestBody BuyProductRequest requestData)throws Exception{
       CustomerDetails response =customerService.buy(requestData);
      return  response;
    }


}
