package com.example.order.controller;

import com.example.order.dto.OrderLineRequest;
import com.example.order.dto.OrderLineResponse;
import com.example.order.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/order-lines")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService service;

    // Add product to an order
    @PostMapping
    public ResponseEntity<Integer> createOrderLine(@RequestBody @Valid OrderLineRequest request) {
        return ResponseEntity.ok(service.createOrderLine(request));
    }

    // Get all order lines for an order
    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(@PathVariable Integer orderId) {
        return ResponseEntity.ok(service.findByOrderId(orderId));
    }
}
