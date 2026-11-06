package com.example.module_product_gradle.module.order.dto.req;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class OrderReqDto {
    public record placeOrderDto(
            @PositiveOrZero Integer quantity
    ) {}

    public record placeTwoOrderDto(
            @NotNull Long productId_1,
            @PositiveOrZero Integer quantity_1,
            @NotNull Long productId_2,
            @PositiveOrZero Integer quantity_2
    ) {}
}
