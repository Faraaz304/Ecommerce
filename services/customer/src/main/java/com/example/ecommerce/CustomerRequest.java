package com.example.ecommerce;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(
        @NotBlank(message = "First name is required")
        String firstName,
        @NotBlank(message = "Last name is required")
        String lastName,
        @Email(message = "Email should be valid")
        String email,
        @NotBlank(message = "Address is required")
        String address,
        String id // optional for update
) {}
