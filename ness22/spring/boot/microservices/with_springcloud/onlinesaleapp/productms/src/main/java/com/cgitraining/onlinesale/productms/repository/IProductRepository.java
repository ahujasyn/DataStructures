package com.cgitraining.onlinesale.productms.repository;

import com.cgitraining.onlinesale.productms.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository  extends JpaRepository<Product,Long> {
}
