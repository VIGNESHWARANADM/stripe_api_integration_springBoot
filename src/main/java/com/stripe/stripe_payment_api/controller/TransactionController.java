package com.stripe.stripe_payment_api.controller;


import com.stripe.stripe_payment_api.dto.TransactionRequest;
import com.stripe.stripe_payment_api.model.Transaction;
import com.stripe.stripe_payment_api.model.User;
import com.stripe.stripe_payment_api.respository.OrderRepository;
import com.stripe.stripe_payment_api.respository.TranscationRepository;
import com.stripe.stripe_payment_api.respository.UserRespository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private UserRespository userRespository;
    private OrderRepository orderRepository;
    private TranscationRepository transcationRepository;

    public TransactionController(UserRespository userRespository, OrderRepository orderRepository, TranscationRepository transcationRepository) {
        this.userRespository = userRespository;
        this.orderRepository = orderRepository;
        this.transcationRepository = transcationRepository;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(
            @RequestBody TransactionRequest request) {
        Transaction transcation = new Transaction();
        transcation.setClientSecretId(request.getClientSecretId());
        transcation.setPaymentMethod(request.getPaymentMethod());
        transcation.setAmount(request.getAmount());
        transcation.setReceiptEmail(request.getReceiptEmail());
        User user = userRespository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));
        transcation.setUser(user);
        Transaction saved = transcationRepository.save(transcation);
        return ResponseEntity.ok(saved);
    }


}
