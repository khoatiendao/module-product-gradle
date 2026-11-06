package com.example.module_product_gradle.module.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.module_product_gradle.module.product.entity.Product;
import org.springframework.data.repository.query.Param;

@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByActiveTrue(Pageable pageable);

    @Query(
            value = "SELECT public.remove_quantity(:productId)",
            nativeQuery = true
    )
    Integer removeQuantity(@Param("productId") Long productId);
}
