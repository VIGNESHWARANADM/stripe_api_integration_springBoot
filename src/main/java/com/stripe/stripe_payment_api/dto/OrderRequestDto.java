package com.stripe.stripe_payment_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {

    private Long userId;

    private Long price; // Price in long (e.g., 234500)

    private Long productId;

    private String clientSecretId;
}