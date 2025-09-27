package com.example.product.mapper;

import com.example.product.dto.*;
import com.example.product.entity.Category;
import com.example.product.entity.Product;

public class ProductMapper {

    // Request → Entity
    public static Product toProduct(ProductRequest request, Category category) {
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .availableQuantity(request.getAvailableQuantity())
                .category(category)
                .build();
    }

    // Entity → Response
    public static ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .availableQuantity(product.getAvailableQuantity())
                .categoryName(
                        product.getCategory() != null ? product.getCategory().getName() : null
                )
                .build();
    }

    // Entity + PurchaseRequest → PurchaseResponse
    public static ProductPurchaseResponse toPurchaseResponse(Product product, double quantity) {
        return ProductPurchaseResponse.builder()
                .productId(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(quantity)
                .build();
    }
}
