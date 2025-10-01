package com.example.payment.entity;

import com.example.payment.dto.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private LocalDateTime paymentDate;

    private String status; // e.g., SUCCESS, FAILED, PENDING

    private Integer orderId;         // reference to order
    private String orderReference;   // like ORD-2025-12345

    private String customerId;       // reference to customer service
}
