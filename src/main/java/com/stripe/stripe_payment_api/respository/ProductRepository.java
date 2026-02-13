package com.stripe.stripe_payment_api.respository;

import com.stripe.stripe_payment_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
