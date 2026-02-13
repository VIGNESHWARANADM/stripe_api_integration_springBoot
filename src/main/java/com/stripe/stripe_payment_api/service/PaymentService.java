package com.stripe.stripe_payment_api.service;

import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.stripe_payment_api.dto.CreatePaymentRequest;
import com.stripe.stripe_payment_api.dto.PaymentIntentResponse;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public PaymentIntent createPayment(CreatePaymentRequest request) throws Exception{
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(request.getAmount())
                .setCurrency(request.getCurrency())
                .setDescription(request.getDescription())
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                .setEnabled(true)
                                .build()
                )
                .build();

        return  PaymentIntent.create(params);
    }
}
