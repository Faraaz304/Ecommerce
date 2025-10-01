package com.example.payment.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentResponse {

    private BigDecimal amount;
    private String paymentMethod;
    private Integer orderId;
    private String orderReference;
    private CustomerResponse customer;
}
