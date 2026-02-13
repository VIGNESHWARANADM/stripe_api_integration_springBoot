package com.stripe.stripe_payment_api.respository;

import com.stripe.stripe_payment_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRespository extends JpaRepository<User, Long> {
    @Query("SELECT count(u) > 0 from User u where u.email = :email ")
    boolean existsByEmail(@Param("email") String email);

    @Query("SELECT u from User u where u.email = :email AND password = :password ")
    User existsByEmailAndPassword(
            @Param("email") String email, @Param("password") String password
    );


}
