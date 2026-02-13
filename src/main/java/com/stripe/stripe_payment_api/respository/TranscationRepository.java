package com.stripe.stripe_payment_api.respository;

import com.stripe.stripe_payment_api.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranscationRepository extends JpaRepository<Transaction, Long> {
}
