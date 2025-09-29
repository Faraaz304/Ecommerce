package com.example.order.service;

import com.example.order.dto.OrderLineRequest;
import com.example.order.dto.OrderLineResponse;
import com.example.order.entity.Order;
import com.example.order.entity.OrderLine;
import com.example.order.mapper.OrderLineMapper;
import com.example.order.repository.OrderLineRepository;
import com.example.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.order.exception.OrderLineNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderRepository orderRepository;
    private final OrderLineRepository lineRepository;

    public Integer createOrderLine(OrderLineRequest request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new OrderLineNotFoundException(request.getOrderId()));

        OrderLine line = OrderLineMapper.toEntity(request);
        line.setOrder(order);

        OrderLine saved = lineRepository.save(line);
        return saved.getId(); // return line ID
    }

    public List<OrderLineResponse> findByOrderId(Integer orderId) {
        return lineRepository.findByOrderId(orderId)
                .stream()
                .map(OrderLineMapper::toDto)
                .toList();
    }
}
