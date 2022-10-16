package com.cgitraining.onlinesale.productms.util;

import com.cgitraining.onlinesale.productms.dto.ProductDetails;
import com.cgitraining.onlinesale.productms.entities.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ProductUtil {

    public ProductDetails toDetails(Product product){
        ProductDetails desired=new ProductDetails();
        desired.setId(product.getId());
        desired.setName(product.getName());
        desired.setPrice(product.getPrice());
        return desired;
    }

    public List<ProductDetails> toDetailsList(Collection<Product>products){
        List<ProductDetails>desiredList=new ArrayList<>();
        for (Product product:products){
            ProductDetails desired=toDetails(product);
            desiredList.add(desired);
        }
        return desiredList;
    }


}
