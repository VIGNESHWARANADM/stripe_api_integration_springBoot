package com.stripe.stripe_payment_api.service;


import com.stripe.stripe_payment_api.config.ZoomConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class ZoomAuthService {

    private final ZoomConfig config;
    private String accessToken;

    public String getAccessToken() {

        String url = "https://zoom.us/oauth/token?grant_type=account_credentials&account_id="
                + config.getAccountId();

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(config.getClientId(), config.getClientSecret());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, Map.class
        );
        System.out.println("REST Response");
        System.out.println(response);

        accessToken = (String) response.getBody().get("access_token");

        return accessToken;
    }
}
