package com.example.demo.customerms.service;

import com.example.demo.JwtTokenUtil;
import com.example.demo.customerms.dto.CustomerDetails;
import com.example.demo.customerms.dto.RegisterCustomerRequest;
import com.example.demo.customerms.entity.Customer;
import com.example.demo.customerms.repo.ICustomerRepository;
import com.example.demo.customerms.util.CustomerUtil;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.user.entity.User;
import com.example.demo.user.repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepo;
    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private CustomerUtil customerUtil;


    @Override
    public CustomerDetails register(RegisterCustomerRequest request) {
        Set<String>roles=new HashSet<>();
        roles.add("CUSTOMER");
        User user = new User(request.getUsername(), request.getPassword(),roles);
        Customer customer = new Customer();
        customer.setUser(user);
        user.setRoles(roles);
        customer.setBalance(request.getBalance());
        customer = customerRepo.save(customer);
        return customerUtil.toDetails(customer);
    }

    @Override
    public List<CustomerDetails> findAll(){
        List<Customer>customers=customerRepo.findAll();
        return customerUtil.toDetailsList(customers);
    }

    @Override
    public CustomerDetails findByUsername(String username) throws UserNotFoundException {
        Optional<Customer> optional = customerRepo.findByUsername(username);
        if (!optional.isPresent()) {
            throw new UserNotFoundException("usernot found " + username);
        }
        Customer customer = optional.get();
        return customerUtil.toDetails(customer);
    }

}
