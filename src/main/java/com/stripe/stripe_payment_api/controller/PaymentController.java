package com.stripe.stripe_payment_api.controller;

import com.stripe.model.PaymentIntent;
import com.stripe.stripe_payment_api.dto.CreatePaymentRequest;
import com.stripe.stripe_payment_api.dto.PaymentIntentResponse;
import com.stripe.stripe_payment_api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentIntentResponse> createPayment(
            @RequestBody CreatePaymentRequest request) {
        try {
//            System.out.println(request + " " +"ssssssss");
            PaymentIntent intent = paymentService.createPayment(request);
            System.out.println(intent + " "+"intent");
            PaymentIntentResponse response = new PaymentIntentResponse(
                    intent.getId(),
                    intent.getClientSecret(),
                    intent.getAmount(),
                    intent.getCurrency(),
                    intent.getStatus()
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println(e + " " + "Exception");
            return  ResponseEntity.status(500).build();
        }
    }
}
