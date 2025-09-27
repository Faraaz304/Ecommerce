package com.example.ecommerce;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        String address
) {
    public static CustomerResponse fromEntity(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
