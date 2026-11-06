package com.example.module_product_gradle.module.user.entity;

import com.example.module_product_gradle.common.commonEnum.UserRoleEnum;
import com.example.module_product_gradle.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "email", unique = true, nullable = false, length = 255)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRoleEnum role = UserRoleEnum.USER;

    @Column(name = "refresh_token", nullable = true)
    private String refreshToken;
}
