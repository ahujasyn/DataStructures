package com.cgitraining.onlinesale.customerms.service;

import com.cgitraining.onlinesale.customerms.dto.BuyProductRequest;
import com.cgitraining.onlinesale.customerms.dto.CustomerDetails;
import com.cgitraining.onlinesale.customerms.dto.RegisterCustomer;
import com.cgitraining.onlinesale.customerms.exceptions.CustomerNotFoundException;
import com.cgitraining.onlinesale.productms.exceptions.ProductNotFoundException;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
public interface ICustomerService {

    CustomerDetails register(@Valid RegisterCustomer requestData);

    CustomerDetails findCustomerDetailsById(@Min(1)long id) throws CustomerNotFoundException;

    CustomerDetails buy(@Valid BuyProductRequest requestData) throws CustomerNotFoundException, ProductNotFoundException;


}
