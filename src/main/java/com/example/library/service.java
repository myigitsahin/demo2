package com.example.library;

import com.example.model.TransactionReportResponse;
import com.example.model.Transaction;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yigit Sahin on 18.3.2017.
 */
public class service {

   // for raw data testing
    public ResponseEntity<String> getList(String fromDate, String toDate, String token)
    {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("fromDate", fromDate);
        vars.put("toDate", toDate);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", token);
        HttpEntity<?> httpEntity = new HttpEntity<Object>(vars, requestHeaders);

        ResponseEntity<String> s = restTemplate.postForEntity("https://testreportingapi.clearsettle.com/api/v3/transaction/list", httpEntity, String.class, vars);
        return s;
    }
    // Get Transaction
    public ResponseEntity<Transaction> getTransaction(String transactionId, String token)
    {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("transactionId", transactionId);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", token);
        HttpEntity<?> httpEntity = new HttpEntity<Object>(vars, requestHeaders);

        ResponseEntity<Transaction> s = restTemplate.postForEntity("https://testreportingapi.clearsettle.com/api/v3/transaction", httpEntity, Transaction.class, vars);
        return s;
    }
    //Get Client
    public ResponseEntity<Transaction> getClient(String transactionId, String token)
    {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("transactionId", transactionId);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", token);
        HttpEntity<?> httpEntity = new HttpEntity<Object>(vars, requestHeaders);

        ResponseEntity<Transaction> s = restTemplate.postForEntity("https://testreportingapi.clearsettle.com/api/v3/client", httpEntity, Transaction.class, vars);
        return s;
    }

    //Get Transaction Report
    public ResponseEntity<TransactionReportResponse> getTransactionList(String fromDate, String toDate, String token)
    {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("fromDate", fromDate);
        vars.put("toDate", toDate);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", token);
        HttpEntity<?> httpEntity = new HttpEntity<Object>(vars, requestHeaders);

        ResponseEntity<TransactionReportResponse> tr = restTemplate.postForEntity("https://testreportingapi.clearsettle.com/api/v3/transactions/report", httpEntity, TransactionReportResponse.class, vars);
        return tr;
    }

}
