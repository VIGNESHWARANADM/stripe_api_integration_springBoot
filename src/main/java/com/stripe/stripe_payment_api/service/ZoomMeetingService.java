package com.stripe.stripe_payment_api.service;

import com.stripe.stripe_payment_api.config.ZoomConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ZoomMeetingService {

    private final ZoomAuthService authService;
    private final ZoomConfig config;

    public String createMeeting() {

        String token = authService.getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("topic", "Spring Boot Meeting");
        body.put("type", 2);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.postForEntity(
                config.getBaseUrl() + "/users/me/meetings",
                entity,
                String.class
        );

        return response.getBody();
    }
}
