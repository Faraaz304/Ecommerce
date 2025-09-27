package com.example.ecommerce;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public String createCustomer(CustomerRequest request) {
        // Prevent duplicate emails
        if (repository.existsByEmail(request.email())) {
            throw new RuntimeException("Customer with email " + request.email() + " already exists");
        }

        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();

        return repository.save(customer).getId();
    }

    public void updateCustomer(CustomerRequest request) {
        Customer customer = repository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + request.id()));

        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setAddress(request.address());

        repository.save(customer);
    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(CustomerResponse::fromEntity)
                .toList();
    }

    public boolean existsById(String customerId) {
        return repository.existsById(customerId);
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(CustomerResponse::fromEntity)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + customerId));
    }

    public void deleteCustomer(String customerId) {
        if (!repository.existsById(customerId)) {
            throw new RuntimeException("Customer not found with id " + customerId);
        }
        repository.deleteById(customerId);
    }
}
