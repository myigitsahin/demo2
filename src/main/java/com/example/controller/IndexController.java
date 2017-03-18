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

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.*;


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
        //ResponseEntity<String> s = restTemplate.postForEntity("https://testreportingapi.clearsettle.com/api/v3/merchant/user/login", httpEntity, String.class, vars);
        //System.out.println(s);
        model.addAttribute("name", u.getToken());
        return "index";
    }
    @Test
    public void testLogin( String email, String pass ) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("email", email);
        vars.put("password", pass);
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<?> httpEntity = new HttpEntity<Object>(vars, requestHeaders);
        restTemplate.postForObject("https://testreportingapi.clearsettle.com/api/v3/merchant/user/login", httpEntity, User.class, vars);
        
    }

    @RequestMapping("/list")
    //@ResponseBody
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

/*
    @RequestMapping("/json")
    public @ResponseBody
    List json() {
        ArrayList<User> a = new ArrayList<User>();
        a.add(new User("Emre", "online", 34));
        a.add(new User("Yiğit", "offline", 31));

        OptionalDouble b = a.stream()
                .map(User -> User.getAge())
                .mapToInt(Integer::intValue)
                .average();

        System.out.println(b);

        return a.stream()
                .filter(f -> f.getStatus().equals("offline")).collect(Collectors.toList());
    }*/
}