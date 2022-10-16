package com.cgitraining.onlinesale.customerms.repository;

import com.cgitraining.onlinesale.customerms.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
}
