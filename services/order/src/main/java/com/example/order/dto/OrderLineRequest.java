package com.example.order.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLineRequest {
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Double price;
}
