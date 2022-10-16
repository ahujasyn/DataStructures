package com.example.demo.customerms.repo;

import com.example.demo.customerms.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
    @Query("from Customer c join c.user u where u.username=:username")
    Optional<Customer> findByUsername(String username);
}
