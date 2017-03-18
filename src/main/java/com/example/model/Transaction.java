package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Yigit Sahin on 18.3.2017.
 */
public class Transaction {
    public Transaction() {
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    @JsonProperty("customerInfo")
    CustomerInfo customerInfo;
}
