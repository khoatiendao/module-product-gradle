package com.example.module_product_gradle.module.order.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderProcedureRepository {
    private final JdbcTemplate jdbcTemplate;

    public void placeOrder(Long productId, Integer quantity) {
        jdbcTemplate.execute("CALL place_order(?, ?)", (PreparedStatementCallback<Void>)
                ps -> {
                    ps.setLong(1, productId);
                    ps.setInt(2, quantity);
                    ps.execute();

                    return null;
                }
        );
    }

    public void placeTwoOrder(Long productId_1, Integer quantity_1, Long productId_2, Integer quantity_2) {
        jdbcTemplate.update("CALL place_two_order(?, ?, ?, ?)",
                productId_1, quantity_1, productId_2, quantity_2);
    }
}
