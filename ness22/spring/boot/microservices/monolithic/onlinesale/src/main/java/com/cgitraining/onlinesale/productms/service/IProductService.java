package com.cgitraining.onlinesale.productms.service;

import com.cgitraining.onlinesale.productms.dto.AddProduct;
import com.cgitraining.onlinesale.productms.dto.ProductDetails;
import com.cgitraining.onlinesale.productms.entities.Product;
import com.cgitraining.onlinesale.productms.exceptions.ProductNotFoundException;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
public interface IProductService {


    ProductDetails add(@Valid AddProduct requestDto);

    ProductDetails findProductDetailsById(@Min(1)long id) throws ProductNotFoundException;


    Product findById(long id) throws ProductNotFoundException;
}