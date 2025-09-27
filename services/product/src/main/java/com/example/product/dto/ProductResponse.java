package com.example.product.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductResponse {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private double availableQuantity;
    private String categoryName;
}
