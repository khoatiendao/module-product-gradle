package com.example.module_product_gradle.module.product.entity;

import java.math.BigDecimal;

import com.example.module_product_gradle.common.entity.BaseEntity;

import com.example.module_product_gradle.module.category.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "products")
@NoArgsConstructor
public class Product extends BaseEntity {
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "price", nullable = false)
    @Positive
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    @PositiveOrZero
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
