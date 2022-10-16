package com.cgitraining.onlinesale.customerms.service;

import com.cgitraining.onlinesale.customerms.dto.BuyProductRequest;
import com.cgitraining.onlinesale.customerms.dto.CustomerDetails;
import com.cgitraining.onlinesale.customerms.dto.RegisterCustomer;
import com.cgitraining.onlinesale.customerms.entity.Customer;
import com.cgitraining.onlinesale.customerms.exceptions.CustomerNotFoundException;
import com.cgitraining.onlinesale.customerms.repository.ICustomerRepository;
import com.cgitraining.onlinesale.customerms.util.CustomerUtil;
import com.cgitraining.onlinesale.productms.entities.Product;
import com.cgitraining.onlinesale.productms.exceptions.ProductNotFoundException;
import com.cgitraining.onlinesale.productms.service.IProductService;
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

    @Autowired
    private IProductService productService;

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
    public CustomerDetails buy(BuyProductRequest requestData) throws CustomerNotFoundException, ProductNotFoundException {
        Product product = productService.findById(requestData.getProductId());
        Customer customer = findById(requestData.getCustomerId());
        Set<Product> products = customer.getProducts();
        if (product == null) {
            products = new HashSet<>();
            customer.setProducts(products);
        }
        products.add(product);
        customerRepo.save(customer);
        CustomerDetails desired = customerUtil.toDetails(customer);
        return desired;

    }
}
