package com.example.notification.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PaymentConfirmation {
    private String orderReference;
    private BigDecimal amount;
    private String paymentMethod;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
}