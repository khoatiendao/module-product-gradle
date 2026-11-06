package com.example.module_product_gradle.module.category.dto.req;

import com.example.module_product_gradle.common.commonEnum.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoryDtoReq {
    public record create(
            @NotBlank @Size(max = 255) String name,
            @NotNull Status status
    ) {}

    public record update(
            @NotBlank @Size(max = 255) String name,
            @NotNull Status status
    ) {}

    public record changeStatusDto(
            @NotNull Status status
    ) {}
}
