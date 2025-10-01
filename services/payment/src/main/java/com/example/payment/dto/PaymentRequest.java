package com.example.payment.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentRequest {

    @NotNull
    private Integer orderId;

    @NotBlank
    private String customerId;

    @NotNull
    @Min(1)
    private BigDecimal amount;

    @NotBlank
    private String paymentMethod;

    @NotBlank
    private String orderReference; // expected in request as well
}
