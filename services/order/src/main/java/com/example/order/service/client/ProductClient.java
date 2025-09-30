package com.example.order.service.client;

import com.example.order.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "product-service",
        url = "http://localhost:8082/api/v1/products" // adjust if needed
)
public interface ProductClient {

    @GetMapping("/{id}")
    ProductResponse findById(@PathVariable("id") Long id);
}
