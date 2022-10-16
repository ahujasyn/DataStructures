package com.cgitraining.onlinesale.customerms.util;

import com.cgitraining.onlinesale.customerms.dto.CustomerDetails;
import com.cgitraining.onlinesale.customerms.dto.ProductResponseDTO;
import com.cgitraining.onlinesale.customerms.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
public class CustomerUtil {

    @Value("${productms.baseurl}")
    private String baseProductUrl;

    @Autowired
    private RestTemplate restTemplate;

    public CustomerDetails toDetails(Customer customer) {
        CustomerDetails desired = new CustomerDetails();
        desired.setCustomerId(customer.getId());
        desired.setCustomerName(customer.getName());
        desired.setAge(customer.getAge());
        Set<Long> productsId = customer.getProducts();
        if (productsId != null) {
            List<ProductResponseDTO> desiredProducts = fetchProductsById(productsId);
            desired.setBoughtProducts(desiredProducts);
        }
        return desired;
    }


    public List<ProductResponseDTO> fetchProductsById(Collection<Long> productsId) {
        List<ProductResponseDTO> products = new ArrayList<>();
        for (long productId : productsId) {
            ProductResponseDTO product = fetchProductById(productId);
            products.add(product);
        }
        return products;
    }

    public ProductResponseDTO fetchProductById(long productId) {
        String url = baseProductUrl+"/products/byid/" + productId;
        System.out.println("url="+url);
        ProductResponseDTO product = restTemplate.getForObject(url, ProductResponseDTO.class);
        return product;
    }

}
