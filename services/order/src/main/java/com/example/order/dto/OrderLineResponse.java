package com.example.order.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLineResponse {
    private Integer id;
    private Integer productId;
    private Integer quantity;
    private Double price;
}
