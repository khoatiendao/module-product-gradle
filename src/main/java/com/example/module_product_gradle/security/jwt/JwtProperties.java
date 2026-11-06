package com.example.module_product_gradle.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.jwt")
public record JwtProperties(AccessToken accessToken, RefreshToken refreshToken) {
    public record AccessToken(String secret, long accessExpirationMinutes) {}

    public record RefreshToken(long refreshExpirationDays) {}
}
