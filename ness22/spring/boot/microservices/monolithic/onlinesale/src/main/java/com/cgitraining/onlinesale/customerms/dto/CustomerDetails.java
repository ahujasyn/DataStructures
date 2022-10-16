package com.cgitraining.onlinesale.customerms.dto;

import com.cgitraining.onlinesale.productms.dto.ProductDetails;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CustomerDetails {
    private long customerId;
    private String customerName;
    private int age;
    private List<ProductDetails>boughtProducts;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<ProductDetails> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(List<ProductDetails> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDetails that = (CustomerDetails) o;
        return customerId == that.customerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }
}
