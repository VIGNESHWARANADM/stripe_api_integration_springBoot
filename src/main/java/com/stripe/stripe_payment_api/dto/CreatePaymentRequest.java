package com.stripe.stripe_payment_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CreatePaymentRequest {
    @NotNull
    private Long amount;

    @NotNull
    private String currency;

    @NotNull
    private String description;
}
