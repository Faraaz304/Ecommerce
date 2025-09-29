package com.example.order.exception;

public class OrderLineNotFoundException extends RuntimeException {
    public OrderLineNotFoundException(Integer id) {
        super("OrderLine not found with id: " + id);
    }
}
