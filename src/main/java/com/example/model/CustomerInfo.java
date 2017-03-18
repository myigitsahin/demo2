package com.example.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * Created by Yigit Sahin on 18.3.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerInfo {
    public CustomerInfo() {

    }
     private int id;
     private String number;
     private String expiryMonth;
     private String expiryYear;

     private String billingFirstName;

    public String getBillingFirstName() {
        return billingFirstName;
    }

    public void setBillingFirstName(String billingFirstName) {
        this.billingFirstName = billingFirstName;
    }

    public String getBillingLastName() {
        return billingLastName;
    }

    public void setBillingLastName(String billingLastName) {
        this.billingLastName = billingLastName;
    }

    private String billingLastName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    private String email;


}
