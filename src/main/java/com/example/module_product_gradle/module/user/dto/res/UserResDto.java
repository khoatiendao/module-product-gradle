package com.example.module_product_gradle.module.user.dto.res;

import com.example.module_product_gradle.common.commonEnum.UserRoleEnum;
import com.example.module_product_gradle.module.user.entity.User;

import java.time.LocalDateTime;

public class UserResDto {
    public record LoginResponse(
            String accessToken,
            String refreshToken
    ) {}

    public record UserResponse(
            Long id,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Boolean active,
            String email,
            UserRoleEnum role
    ) {
        public static UserResponse from(User user) {
            return new UserResponse(
                    user.getId(),
                    user.getCreatedAt(),
                    user.getUpdateAt(),
                    user.getActive(),
                    user.getEmail(),
                    (UserRoleEnum) user.getRole()
            );
        }
    }
}
