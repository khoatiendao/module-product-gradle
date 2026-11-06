package com.example.module_product_gradle.module.auth.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthReqDto {
    public record register(
            @NotBlank @Email String email,
            @NotBlank @Size(min = 8, max = 20) String password
    ) {}

    public record login(
            @NotBlank @Email String email,
            @NotBlank String password
    ) {}

    public record refreshTokenDto(
            @NotBlank String refreshToken
    ) {}

    public record createAdminDto(
            @NotBlank @Email String email,
            @NotBlank @Size(min = 8, max = 20) String password
    ) {}
}
