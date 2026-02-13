//package com.stripe.stripe_payment_api.controller;
//
//import com.stripe.stripe_payment_api.dto.ProductRequest;
//import com.stripe.stripe_payment_api.dto.StripeResponse;
//import com.stripe.stripe_payment_api.service.StripeService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/products/v1")
//public class ProductCheckoutController {
//
//    private StripeService stripeService;
//
//    public ProductCheckoutController(StripeService stripeService){
//        this.stripeService = stripeService;
//    }
//    @PostMapping("/checkout")
//    public ResponseEntity<StripeResponse> checkoutProducts(
//            @RequestBody ProductRequest productRequest){
//        StripeResponse stripeResponse = stripeService.checkoutProducts(productRequest);
//        return  ResponseEntity.status(HttpStatus.OK)
//                .body(stripeResponse);
//    }
//}
