package com.example.order.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Integer id;
    private Integer customerId;
    private String status;
    private LocalDateTime orderDate;
    private List<OrderLineResponse> orderLines;
}
