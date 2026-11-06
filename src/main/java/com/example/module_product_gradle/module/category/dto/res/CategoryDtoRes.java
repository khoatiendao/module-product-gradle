package com.example.module_product_gradle.module.category.dto.res;

import com.example.module_product_gradle.common.CommonEnum.Status;
import com.example.module_product_gradle.module.category.entity.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CategoryDtoRes(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Boolean active,
        String name,
        Status status
) {
    public static CategoryDtoRes from(Category category) {
        return new CategoryDtoRes(
                category.getId(),
                category.getCreatedAt(),
                category.getUpdateAt(),
                category.getActive(),
                category.getName(),
                (Status) category.getStatus()
        );
    }
}
