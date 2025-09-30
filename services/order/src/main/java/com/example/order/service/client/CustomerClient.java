package com.example.order.service.client;

import com.example.order.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "customer-service",
        url = "http://localhost:8081/api/v1/customers" // base path
)
public interface CustomerClient {

    @GetMapping("/{id}")
    CustomerResponse findById(@PathVariable("id") String id);
}
