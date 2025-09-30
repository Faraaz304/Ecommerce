package com.example.order.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private String id;          // changed from Integer → String
    private String firstName;
    private String lastName;
    private String email;
    private String address;
}
