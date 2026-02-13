//package com.stripe.stripe_payment_api.service;
//
//import com.stripe.Stripe;
//import com.stripe.exception.StripeException;
//import com.stripe.model.checkout.Session;
//import com.stripe.param.checkout.SessionCreateParams;
//import com.stripe.stripe_payment_api.dto.ProductRequest;
//import com.stripe.stripe_payment_api.dto.StripeResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class StripeService {
//    @Value("${stripe.secret.key}")
//    private String secretKey;
//
//    public StripeResponse checkoutProducts(ProductRequest productRequest) {
//        Stripe.apiKey = secretKey;
//        System.out.println(secretKey + " " + "secretKey");
//
//        SessionCreateParams.LineItem.PriceData.ProductData.Builder productData =
//                SessionCreateParams.LineItem.PriceData.ProductData.builder()
//                        .setName(productRequest.getName())
//                        .build();
//
//        SessionCreateParams.LineItem.PriceData priceData =
//                SessionCreateParams.LineItem.PriceData.builder()
//                        .setCurrency(productRequest.getCurrency() == null ? "USD" : productRequest.getCurrency())
//                        .setUnitAmount(productRequest.getAmount())
//                        .build();
//
//        SessionCreateParams.LineItem lineItem =
//                SessionCreateParams
//                        .LineItem.builder()
//                        .setQuantity(productRequest.getQuantity())
//                        .setPriceData(priceData)
//                        .build();
//
//        SessionCreateParams params = SessionCreateParams.builder()
//                .setMode(SessionCreateParams.Mode.PAYMENT)
//                .setSuccessUrl("http://localhost:8080/success")
//                .setCancelUrl("http://localhost:8080/cancel")
//                .addLineItem(lineItem)
//                .build();
//
//        Session session = null;
//        try {
//            System.out.println(session + " " + "session");
//            session = Session.create(params);
//
//        } catch (StripeException e) {
//            System.out.println(e.getMessage());
//        }
//        return StripeResponse.builder()
//                .status("SUCCESS")
//                .message("Payment Session created")
//                .sessionId(session.getId())
//                .sessionUrl(session.getUrl())
//                .build();
//    }
//}
