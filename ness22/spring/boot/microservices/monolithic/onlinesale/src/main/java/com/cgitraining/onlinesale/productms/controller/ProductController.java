package com.cgitraining.onlinesale.productms.controller;

import com.cgitraining.onlinesale.productms.dto.AddProduct;
import com.cgitraining.onlinesale.productms.dto.ProductDetails;
import com.cgitraining.onlinesale.productms.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/products")
@RestController
public class ProductController {

    @Autowired
    private IProductService service;

    @PostMapping("/add")
    public ProductDetails addProduct(@RequestBody AddProduct requestData){
        ProductDetails response=service.add(requestData);
        return response;
    }

    @GetMapping("/byid/{id}")
    public ProductDetails findById(@PathVariable long id)throws Exception
    {
        ProductDetails response=service.findProductDetailsById(id);
        return response;
    }

}
