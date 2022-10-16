package com.cgitraining.onlinesale.customerms.dto;

import javax.validation.constraints.Min;

public class BuyProductRequest {

    @Min(1)
    private long productId;
    @Min(1)
    private long customerId;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
