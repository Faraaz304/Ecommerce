package com.example.notification.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderConfirmation {
    private String orderReference;
    private BigDecimal totalAmount;
    private String paymentMethod;
    private Customer customer;
    private List<Product> products;
}