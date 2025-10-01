package com.example.payment.service;

import com.example.payment.client.CustomerClient;
import com.example.payment.dto.CustomerResponse;
import com.example.payment.dto.PaymentRequest;
import com.example.payment.dto.PaymentResponse;
import com.example.payment.entity.Payment;
import com.example.payment.mapper.PaymentMapper;
import com.example.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final CustomerClient customerClient;

    public PaymentResponse createPayment(PaymentRequest request) {
        // 1. Map request → entity
        Payment payment = mapper.toEntity(request);

        // 2. Save to DB
        repository.save(payment);

        // 3. Call customer service via Feign
        CustomerResponse customer = customerClient.getCustomerById(request.getCustomerId());

        // 4. Map to response
        return mapper.toResponse(payment, customer);
    }
}
