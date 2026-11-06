package com.example.module_product_gradle.common.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public enum ErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "Không tìm thấy dữ liệu"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Yêu cầu không hợp lệ"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Chưa đăng nhập"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "Không có quyền truy cập"),
    CONFLICT(HttpStatus.CONFLICT, "Dữ liệu đã tồn tại"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi hệ thống");

    private final HttpStatus status;
    private final String defaultMessage;
}
