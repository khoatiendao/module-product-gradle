package com.example.module_product_gradle.module.product.dto.res;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.module_product_gradle.module.product.entity.Product;

public record ProductDtoRes(
    Long id,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Boolean active,
    String name,
    BigDecimal price,
    Integer quantity,
    Long categoryId
) {
    public static ProductDtoRes from(Product product) {
        return new ProductDtoRes(
                product.getId(),
                product.getCreatedAt(), 
                product.getUpdateAt(),
                product.getActive(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory().getId()
        );
    }
}
