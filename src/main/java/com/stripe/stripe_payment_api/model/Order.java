package com.stripe.stripe_payment_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="product_id")
    @OneToOne
    private Product productId;

    @JoinColumn(name="user_id")
    @ManyToOne
    private User userId;

    @Column(nullable = false)
    private Long price; // The price of the product at time of order

    @Column(length = 500)
    private String clientSecret;

    @Column(nullable = false)
    private boolean paymentStatus = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


}