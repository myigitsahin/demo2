package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Yigit Sahin on 18.3.2017.
 */
public class TransactionReportResponse {
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("response")
    List<TransactionReport> reports;

    public List<TransactionReport> getReports() {
        return reports;
    }

    public void setReports(List<TransactionReport> reports) {
        this.reports = reports;
    }
}