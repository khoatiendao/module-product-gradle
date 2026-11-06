package com.example.module_product_gradle.module.user.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserFunctionRepository {
    private final JdbcTemplate jdbcTemplate;

    public Integer removeRefreshToken(Long userId) {
        return jdbcTemplate.queryForObject(
                "SELECT remove_refreshtoken(?)",
                Integer.class,
                userId
        );
    }
}
