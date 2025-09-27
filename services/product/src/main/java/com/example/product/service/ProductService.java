package com.example.product.service;

import com.example.product.dto.*;
import com.example.product.entity.Category;
import com.example.product.entity.Product;
import com.example.product.mapper.ProductMapper;
import com.example.product.repository.CategoryRepository;
import com.example.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    /**
     * Create a new Product from ProductRequest DTO.
     */
    public Integer createProduct(ProductRequest request) {
        // Find category (throws error if not found)
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        // Map request → entity
        Product product = ProductMapper.toProduct(request, category);

        // Save product
        productRepository.save(product);

        // Return generated product ID
        return product.getId();
    }

    /**
     * Purchase products (reduce stock & return purchase details).
     */
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) {
        return requests.stream().map(req -> {
            // Fetch product
            Product product = productRepository.findById(req.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));

            // Check available stock
            if (product.getAvailableQuantity() < req.getQuantity()) {
                throw new IllegalArgumentException("Not enough stock for product: " + product.getName());
            }

            // Reduce stock
            product.setAvailableQuantity(product.getAvailableQuantity() - req.getQuantity());
            productRepository.save(product);

            // Return response
            return ProductMapper.toPurchaseResponse(product, req.getQuantity());
        }).toList();
    }

    /**
     * Find product by ID.
     */
    public ProductResponse findById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        return ProductMapper.toResponse(product);
    }

    /**
     * Find all products.
     */
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }
}
