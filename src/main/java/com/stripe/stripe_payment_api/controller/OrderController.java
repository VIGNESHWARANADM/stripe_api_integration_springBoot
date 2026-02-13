package com.stripe.stripe_payment_api.controller;

import com.stripe.stripe_payment_api.dto.OrderRequestDto;
import com.stripe.stripe_payment_api.model.Order;
import com.stripe.stripe_payment_api.model.Product;
import com.stripe.stripe_payment_api.model.User;
import com.stripe.stripe_payment_api.respository.OrderRepository;
import com.stripe.stripe_payment_api.respository.ProductRepository;
import com.stripe.stripe_payment_api.respository.UserRespository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private UserRespository userRespository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    public OrderController(UserRespository userRespository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.userRespository = userRespository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }


    @PostMapping("/checkout")
    public ResponseEntity<Order> checkout(
            @RequestBody OrderRequestDto orderRequestDto
    ) {
        System.out.println(orderRequestDto + " " + "HIII");
        if (orderRequestDto.getClientSecretId() == null || orderRequestDto.getClientSecretId().isEmpty()) {
            throw new IllegalArgumentException("Client Secret ID is required to process the payment.");
        }
        Order order = new Order();
        User user = userRespository.findById(orderRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(orderRequestDto.getProductId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUserId(user);
        order.setProductId(product);
        order.setPrice(orderRequestDto.getPrice());
        order.setClientSecret(orderRequestDto.getClientSecretId());
        order.setPaymentStatus(false);
        order.setCreatedAt(LocalDateTime.now());
        Order saved = orderRepository.save(order);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/confirm/{clientSecretId}")
    public ResponseEntity<Map<String, Object>> updateOrder(
            @PathVariable String clientSecretId
    ) {
        Map<String, Object> response = new LinkedHashMap<>();
        int updatedRows = orderRepository.updatePaymentStatusByClientsecrectId(clientSecretId);
        if (updatedRows > 0) {
            response.put("status", true);
            response.put("message", "Payment processed successfully");

            return ResponseEntity.ok(response);
        } else {
            response.put("status", false);
            response.put("message", "No order found with that Secret ID");
            return ResponseEntity.status(404).body(response);
        }

    }

    @PostMapping("/orders/{userId}")
    public ResponseEntity<Map<String, Object>> getOrders(
            @PathVariable("userId") Long userId
    ) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            List<Order> orders = orderRepository.findByUserId_Id(userId);
            response.put("status", true);
            response.put("message", "orders fetched successfully");
            response.put("data", orders);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", true);
            response.put("message", "failed");
            return ResponseEntity.status(404).body(response);
        }

    }
}
