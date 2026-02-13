package com.stripe.stripe_payment_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private Long price;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public Product(){}
    public Product(User user, String name, Long price, LocalDateTime createdAt) {
        this.user = user;
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }
}
