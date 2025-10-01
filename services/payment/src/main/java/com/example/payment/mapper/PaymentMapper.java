package com.example.payment.mapper;

import com.example.payment.dto.PaymentRequest;
import com.example.payment.dto.PaymentResponse;
import com.example.payment.dto.CustomerResponse;
import com.example.payment.entity.Payment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentMapper {

    // Request → Entity
    public Payment toEntity(PaymentRequest request) {
        return Payment.builder()
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .paymentDate(LocalDateTime.now())
                .status("SUCCESS") // default for now
                .orderId(request.getOrderId())
                .orderReference(request.getOrderReference())
                .customerId(request.getCustomerId())
                .build();
    }

    // Entity + CustomerResponse → PaymentResponse
    public PaymentResponse toResponse(Payment payment, CustomerResponse customer) {
        return PaymentResponse.builder()
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .orderId(payment.getOrderId())
                .orderReference(payment.getOrderReference())
                .customer(customer)
                .build();
    }
}
