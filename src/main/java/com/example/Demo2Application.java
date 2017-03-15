package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Demo2Application {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();

		Map<String, String> vars = new HashMap<String, String>();
		//vars.put("email", "demo@bumin.com.tr");
		//vars.put("password", "cjaiU8CV");

		vars.put("fromDate", "2017-01-02");
		vars.put("toDate", "2017-12-01");

		User u = new User();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtZXJjaGFudFVzZXJJZCI6NTMsInJvbGUiOiJhZG1pbiIsIm1lcmNoYW50SWQiOjMsInN1Yk1lcmNoYW50SWRzIjpbMyw3NCw5MywxMTEsMTM3LDEzOCwxNDIsMTQ1LDE0NiwxNTMsMzM0LDE3NSwxODQsMjIwLDIyMSwyMjIsMjIzLDI5NCwzMjIsMzIzLDMyNywzMjksMzMwLDM0OSwzOTAsMzkxLDQ1NSw0NTYsNDc5LDQ4OCw1NjMsNTcwXSwidGltZXN0YW1wIjoxNDg5NTg1ODA5fQ.wjLfBIV1RdBtX0MNxkzq7iDWwPclvdFf52MESy39EoY");
		HttpEntity<?> httpEntity = new HttpEntity<Object>(vars, requestHeaders);

		//ResponseEntity<String> s = restTemplate.postForEntity("https://testreportingapi.clearsettle.com/api/v3/merchant/user/login", httpEntity, String.class, vars);

		ResponseEntity<String> s = restTemplate.postForEntity("https://testreportingapi.clearsettle.com/api/v3/transactions/report", httpEntity, String.class, vars);

		System.out.println(s);


		//Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		//System.out.println(quote.toString());
		SpringApplication.run(Demo2Application.class, args);
	}
}
