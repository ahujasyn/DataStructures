package com.cgitraining.onlinesale.customerms.service;

import com.cgitraining.onlinesale.customerms.dto.BuyProductRequest;
import com.cgitraining.onlinesale.customerms.dto.CustomerDetails;
import com.cgitraining.onlinesale.customerms.dto.RegisterCustomer;
import com.cgitraining.onlinesale.customerms.entity.Customer;
import com.cgitraining.onlinesale.customerms.exceptions.CustomerNotFoundException;
import com.cgitraining.onlinesale.customerms.repository.ICustomerRepository;
import com.cgitraining.onlinesale.customerms.util.CustomerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepo;

    @Autowired
    private CustomerUtil customerUtil;

    @Transactional
    @Override
    public CustomerDetails register(RegisterCustomer requestData) {
        Customer customer = new Customer();
        customer.setName(requestData.getName());
        customer.setAge(requestData.getAge());
        customer = customerRepo.save(customer);
        CustomerDetails desired = customerUtil.toDetails(customer);
        return desired;
    }

    @Override
    public CustomerDetails findCustomerDetailsById(long id) throws CustomerNotFoundException {
        Customer customer = findById(id);
        CustomerDetails desired = customerUtil.toDetails(customer);
        return desired;
    }

    public Customer findById(long id) throws CustomerNotFoundException {
        Optional<Customer> customerOptional = customerRepo.findById(id);
        if (!customerOptional.isPresent()) {
            throw new CustomerNotFoundException("customer not found by id=" + id);
        }
        return customerOptional.get();

    }


    @Override
    public CustomerDetails buy(BuyProductRequest requestData) throws CustomerNotFoundException{
        Customer customer = findById(requestData.getCustomerId());
        Set<Long> productsId = customer.getProducts();
        if (productsId == null) {
            productsId = new HashSet<>();
            customer.setProducts(productsId);
        }
        productsId.add(requestData.getProductId());
        customerRepo.save(customer);
        CustomerDetails desired = customerUtil.toDetails(customer);
        return desired;

    }
}
