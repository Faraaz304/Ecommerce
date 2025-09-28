package com.example.order.service;

import com.example.order.dto.OrderRequest;
import com.example.order.dto.OrderResponse;
import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import com.example.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    // Create a new order
    public OrderResponse createOrder(OrderRequest request) {
        // Convert DTO to Entity
        Order order = OrderMapper.toEntity(request);

        // Save to DB
        Order savedOrder = orderRepository.save(order);

        // Convert back to DTO and return
        return OrderMapper.toDto(savedOrder);
    }

    // Get order by ID
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        return OrderMapper.toDto(order);
    }

    // Get all orders
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(OrderMapper::toDto).toList();
    }
}
