package com.stripe.stripe_payment_api.dto;


import lombok.Data;

@Data
public class TransactionRequest {

    private String clientSecretId;
    private String paymentMethod;
    private Long amount;
    private String receiptEmail;
    private Long userId;
}