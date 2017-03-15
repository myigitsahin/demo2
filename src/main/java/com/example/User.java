package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by User on 15.3.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String token;
    private String status;

    @Override
    public String toString() {
        return this.token+" : " + this.status;
    }
}
