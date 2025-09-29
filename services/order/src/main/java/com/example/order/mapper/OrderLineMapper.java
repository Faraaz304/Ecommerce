package com.example.order.mapper;

import com.example.order.dto.OrderLineRequest;
import com.example.order.dto.OrderLineResponse;
import com.example.order.entity.OrderLine;

public class OrderLineMapper {

    public static OrderLine toEntity(OrderLineRequest request) {
        return OrderLine.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();
    }

    public static OrderLineResponse toDto(OrderLine line) {
        return OrderLineResponse.builder()
                .id(line.getId())
                .productId(line.getProductId())
                .quantity(line.getQuantity())
                .price(line.getPrice())
                .build();
    }
}
