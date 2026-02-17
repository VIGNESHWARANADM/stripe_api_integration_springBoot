package com.stripe.stripe_payment_api.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "zoom")
@Component
@Data
public class ZoomConfig {
    @Value("${zoom.oauth2.account-id}")
    private String accountId;
    @Value("${zoom.oauth2.client-id}")
    private String clientId;
    @Value("${zoom.oauth2.client-secret}")
    private String clientSecret;
    private String baseUrl;
}
