package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * Created by Yigit Sahin on 18.3.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class TransactionReport {

    private int count;
    private int total;
    private String currency;

    public TransactionReport() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
