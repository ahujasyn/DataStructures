package com.example.demo.customerms.util;

import com.example.demo.customerms.dto.CustomerDetails;
import com.example.demo.customerms.entity.Customer;
import com.example.demo.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerUtil {

    public CustomerDetails toDetails(Customer customer){
        User user=customer.getUser();
        CustomerDetails desired=new CustomerDetails();
        desired.setUsername(user.getUsername());
        desired.setBalance(customer.getBalance());
        desired.setId(customer.getId());
        return desired;
    }

    public List<CustomerDetails> toDetailsList(Collection<Customer>customers){
        return customers.stream().map(customer -> toDetails(customer)).collect(Collectors.toList());
    }

}
