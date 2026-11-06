package com.example.module_product_gradle.module.category.repository;

import com.example.module_product_gradle.common.CommonEnum.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.module_product_gradle.module.category.entity.Category;

import java.util.Optional;

@EnableJpaRepositories
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findByActiveTrueAndStatus(Status status, Pageable pageable);
}
