package com.example.order.service;

import com.example.order.dto.OrderRequest;
import com.example.order.dto.OrderResponse;
import com.example.order.entity.Order;
import com.example.order.mapper.OrderMapper;
import com.example.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public Integer createOrder(OrderRequest request) {
        Order order = OrderMapper.toEntity(request);
        Order saved = repository.save(order);
        return saved.getId(); // return only order ID
    }

    public List<OrderResponse> findAllOrders() {
        return repository.findAll()
                .stream()
                .map(OrderMapper::toDto)
                .toList();
    }

    public OrderResponse findById(Integer id) {
        return repository.findById(id)
                .map(OrderMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }
}
