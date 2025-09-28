package com.example.order.mapper;

import com.example.order.dto.OrderRequest;
import com.example.order.dto.OrderResponse;
import com.example.order.entity.Order;
import com.example.order.entity.OrderLine;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    // Convert DTO → Entity
    public static Order toEntity(OrderRequest request) {
        Order order = Order.builder()
                .customerId(request.getCustomerId())
                .status("PENDING")
                .build();

        List<OrderLine> orderLines = request.getOrderLines().stream()
                .map(lineReq -> OrderLine.builder()
                        .productId(lineReq.getProductId())
                        .quantity(lineReq.getQuantity())
                        .price(lineReq.getPrice())
                        .order(order) // set back-reference
                        .build())
                .collect(Collectors.toList());

        order.setOrderLines(orderLines);
        return order;
    }

    // Convert Entity → DTO
    public static OrderResponse toDto(Order order) {
        List<OrderResponse.OrderLineResponse> lines = order.getOrderLines().stream()
                .map(line -> OrderResponse.OrderLineResponse.builder()
                        .productId(line.getProductId())
                        .quantity(line.getQuantity())
                        .price(line.getPrice())
                        .build())
                .collect(Collectors.toList());

        return OrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .status(order.getStatus())
                .orderLines(lines)
                .build();
    }
}
