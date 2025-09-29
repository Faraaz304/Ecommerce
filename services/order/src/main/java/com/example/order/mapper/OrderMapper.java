package com.example.order.mapper;

import com.example.order.dto.OrderRequest;
import com.example.order.dto.OrderResponse;
import com.example.order.entity.Order;

import java.time.LocalDateTime;
import java.util.Collections;

public class OrderMapper {

    public static Order toEntity(OrderRequest request) {
        return Order.builder()
                .customerId(request.getCustomerId())
                .status("PENDING") // default status
                .orderDate(LocalDateTime.now())
                .orderLines(Collections.emptyList())
                .build();
    }

    public static OrderResponse toDto(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .status(order.getStatus())
                .orderDate(order.getOrderDate())
                .orderLines(
                        order.getOrderLines() == null
                                ? Collections.emptyList()
                                : order.getOrderLines().stream()
                                    .map(OrderLineMapper::toDto)
                                    .toList()
                )
                .build();
    }
}
