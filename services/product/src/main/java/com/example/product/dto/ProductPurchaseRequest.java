package com.example.product.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductPurchaseRequest {
    private Long productId;
    private double quantity;
}
