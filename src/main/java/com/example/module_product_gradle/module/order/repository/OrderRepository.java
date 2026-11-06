package com.example.module_product_gradle.module.order.repository;

import com.example.module_product_gradle.module.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}
