package com.cgitraining.onlinesale.customerms.util;

import com.cgitraining.onlinesale.customerms.dto.CustomerDetails;
import com.cgitraining.onlinesale.customerms.entity.Customer;
import com.cgitraining.onlinesale.productms.dto.ProductDetails;
import com.cgitraining.onlinesale.productms.entities.Product;
import com.cgitraining.onlinesale.productms.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class CustomerUtil {

    @Autowired
    private ProductUtil productUtil;

    public CustomerDetails toDetails(Customer customer){
        CustomerDetails desired=new CustomerDetails();
        desired.setCustomerId(customer.getId());
        desired.setCustomerName(customer.getName());
        desired.setAge(customer.getAge());
        Set<Product> products=customer.getProducts();
        if(products!=null) {
            List<ProductDetails> desiredProducts=productUtil.toDetailsList(products);
            desired.setBoughtProducts(desiredProducts);
        }
        return desired;
    }

}
