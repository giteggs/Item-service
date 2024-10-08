package com.org.Item.entity.extrenalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ExternalApiService {
	
    private static final String GOOGLE_URL = "https://www.google.com";

    @Autowired
    private RestTemplate restTemplate;

    // Method to check if the service is available
    public boolean isServiceAvailable() {
        try {
            // Use the exchange method to send a HEAD request
            ResponseEntity<Void> response = restTemplate.exchange(GOOGLE_URL, HttpMethod.HEAD, null, Void.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false; // Service is unavailable
        }
    }

}
