package com.example.module_product_gradle.module.product.dto.req;

import java.math.BigDecimal;

import jakarta.validation.constraints.*;

public class ProductDtoReq {
    public record Create(
        @NotBlank @Size(max = 255) String name,
        @Positive BigDecimal price,
        @PositiveOrZero Integer quantity,
        @NotNull Long categoryId
    ) {}

    public record Update(
        @NotBlank @Size(max = 255) String name,
        @Positive BigDecimal price,
        @PositiveOrZero Integer quantity,
        @NotNull Long categoryId
    ) {}
}
