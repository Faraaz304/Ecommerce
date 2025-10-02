package com.example.ecommerce;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customers")
public class Customer {
    @Id
    private String id; // MongoDB will auto-generate this
    private String firstName;
    private String lastName;
    private String email;
    private String address;
}
