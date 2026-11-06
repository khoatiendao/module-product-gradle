package com.example.module_product_gradle.module.category.entity;

import com.example.module_product_gradle.common.commonEnum.Status;
import com.example.module_product_gradle.common.entity.BaseEntity;
import com.example.module_product_gradle.module.product.entity.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "categories")
@Entity
public class Category extends BaseEntity {
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.ACTIVE;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();
}
