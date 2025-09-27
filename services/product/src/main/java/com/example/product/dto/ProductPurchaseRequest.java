package com.example.product.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductPurchaseRequest {
    private Integer productId;
    private double quantity;
}
