package com.example.payment.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerResponse {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
}
