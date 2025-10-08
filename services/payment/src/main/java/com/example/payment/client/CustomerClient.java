package com.example.payment.client;

import com.example.payment.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Change "customer-service" to the actual service name registered in Eureka (if using service discovery)
@FeignClient(name = "customer-service", url = "http://localhost:8081/api/customers")
public interface CustomerClient {

    @GetMapping("/{id}")
    CustomerResponse getCustomerById(@PathVariable("id") String id);
}

