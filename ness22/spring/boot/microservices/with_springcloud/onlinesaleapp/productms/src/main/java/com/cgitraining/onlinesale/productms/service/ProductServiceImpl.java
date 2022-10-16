package com.cgitraining.onlinesale.productms.service;

import com.cgitraining.onlinesale.productms.util.ProductUtil;
import com.cgitraining.onlinesale.productms.dto.AddProduct;
import com.cgitraining.onlinesale.productms.dto.ProductDetails;
import com.cgitraining.onlinesale.productms.entities.Product;
import com.cgitraining.onlinesale.productms.exceptions.ProductNotFoundException;
import com.cgitraining.onlinesale.productms.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepo;

    @Autowired
    private ProductUtil productUtil;

    @Transactional
    @Override
    public ProductDetails add(AddProduct requestDto) {
        Product product = new Product();
        product.setName(requestDto.getName());
        product.setPrice(requestDto.getPrice());
        product=productRepo.save(product);
        ProductDetails desired = productUtil.toDetails(product);
        return desired;
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDetails findProductDetailsById(long id) throws ProductNotFoundException {
        Product product = findById(id);
        ProductDetails desired = productUtil.toDetails(product);
        return desired;

    }


    @Transactional(readOnly = true)
    public Product findById(long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepo.findById(id);
        if (!productOptional.isPresent()) {
            throw new ProductNotFoundException("product not found for id="+id);
        }
        Product product = productOptional.get();
       return product;

    }
}
