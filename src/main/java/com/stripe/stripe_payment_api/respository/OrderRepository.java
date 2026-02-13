package com.stripe.stripe_payment_api.respository;

import com.stripe.stripe_payment_api.model.Order;
import com.stripe.stripe_payment_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.paymentStatus = true WHERE o.clientSecret = :id")
    int updatePaymentStatusByClientsecrectId(@Param("id") String clientSId);


    List<Order> findByUserId_Id(Long userId);
}
