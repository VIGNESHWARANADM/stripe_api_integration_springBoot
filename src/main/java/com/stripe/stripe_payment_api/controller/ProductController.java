package com.stripe.stripe_payment_api.controller;

import com.stripe.stripe_payment_api.model.Product;
import com.stripe.stripe_payment_api.model.User;
import com.stripe.stripe_payment_api.respository.ProductRepository;
import com.stripe.stripe_payment_api.respository.UserRespository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private UserRespository userRespository;
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository, UserRespository userRespository) {
        this.productRepository = productRepository;
        this.userRespository = userRespository;
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(
            @RequestParam String name,
            @RequestParam Long price,
            @RequestParam Long userId
    ){
        User user = userRespository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + userId));
        Product product = new Product(user,name,price, LocalDateTime.now());
        Product saved = productRepository.save(product);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> products =  productRepository.findAll();
        return ResponseEntity.ok(products);
    }
}
