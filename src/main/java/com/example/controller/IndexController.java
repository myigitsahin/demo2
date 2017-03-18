package com.example.controller;
import com.example.library.service;
import com.example.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/**
 * Created by Yigit Sahin on 18.3.2017.
 */
import com.example.model.TransactionReportResponse;
import com.example.model.TransactionReport;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.coyote.http11.Constants.a;


@Controller
public class IndexController {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
   @Autowired
   private User httpSession;

    @RequestMapping("/index")
    public String token(@RequestParam(value="email", required=false, defaultValue="email")  String email, @RequestParam(value="pass", required=false, defaultValue="password") String pass,  Model model)
    {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("email", email);
        vars.put("password", pass);
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<?> httpEntity = new HttpEntity<Object>(vars, requestHeaders);
        User u = restTemplate.postForObject("https://testreportingapi.clearsettle.com/api/v3/merchant/user/login", httpEntity, User.class, vars);
        if (u.getStatus().equals("APPROVED")) {
            httpSession = u;
        }
        model.addAttribute("name", u.getToken());
        return "index";
    }
    //login unit test incomplete
    @Test
    public void testLogin( String email, String pass ) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("email", email);
        vars.put("password", pass);
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<?> httpEntity = new HttpEntity<Object>(vars, requestHeaders);
        User u =restTemplate.postForObject("https://testreportingapi.clearsettle.com/api/v3/merchant/user/login", httpEntity, User.class, vars);
        Assert.isNull(u, "Incorrect email or password");

    }

    @RequestMapping("/list")
    public String list(
                       @RequestParam(value="fromDate", required=false, defaultValue="password") String fromDate,
                       @RequestParam(value="toDate", required=false, defaultValue="password") String toDate,  Model model)
    {

        service aas= new service();
        ResponseEntity<String> s = aas.getList(fromDate, toDate, httpSession.getToken());
        model.addAttribute("name", "newa:" + s);
        return "index";
    }

    @RequestMapping("/transaction")
    public @ResponseBody
    Transaction transaction(
            @RequestParam(value="transactionID", required=false, defaultValue="transactionID") String transactionID,  Model model)
    {
        service aas= new service();
        return aas.getTransaction(transactionID, httpSession.getToken()).getBody();
    }

    @RequestMapping("/client")
    public @ResponseBody
    Transaction client(
            @RequestParam(value="transactionID", required=false, defaultValue="transactionID") String transactionID,  Model model)
    {
        service aas= new service();
        return aas.getClient(transactionID, httpSession.getToken()).getBody();
    }

    @RequestMapping("/tlist")
    public @ResponseBody  TransactionReportResponse tlist(
            @RequestParam(value="fromDate", required=false, defaultValue="password") String fromDate,
            @RequestParam(value="toDate", required=false, defaultValue="password") String toDate,  Model model)
    {

        service aas= new service();
        return aas.getTransactionList(fromDate, toDate, httpSession.getToken()).getBody();

    }

    // some of new java 8 features Bulk Data Operations on Collections, Lambda Expressions
    @RequestMapping("/average")
    public @ResponseBody  OptionalDouble average(
            @RequestParam(value="fromDate", required=false, defaultValue="password") String fromDate,
            @RequestParam(value="toDate", required=false, defaultValue="password") String toDate,  Model model)
    {

        service aas= new service();
        TransactionReportResponse av = new TransactionReportResponse();
        av = aas.getTransactionList(fromDate, toDate, httpSession.getToken()).getBody();
        List<TransactionReport> a = new ArrayList<TransactionReport>();
        a= av.getReports();
        OptionalDouble b =  a.stream()
                .map(TransactionReport -> TransactionReport.getTotal())
                .mapToInt(Integer::intValue)
                .average();

        return b;

    }

}