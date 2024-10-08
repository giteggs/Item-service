package com.org.Item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/external")
public class ExternalApiController {
	

	  private static final String GOOGLE_URL = "https://www.google.com";

	    @Autowired
	    private RestTemplate restTemplate; 

	    
	    @GetMapping("/google")
	    public String callGoogle() {
	        return restTemplate.getForObject(GOOGLE_URL, String.class);
	    }

	    
	    @GetMapping("/google/available")
	    public boolean isServiceAvailable() {
	        try {
	            ResponseEntity<Void> response = restTemplate.exchange(GOOGLE_URL, HttpMethod.HEAD, null, Void.class);
	            return response.getStatusCode().is2xxSuccessful();
	        } catch (Exception e) {
	            return false; // Service is unavailable
	        }
	    }
}