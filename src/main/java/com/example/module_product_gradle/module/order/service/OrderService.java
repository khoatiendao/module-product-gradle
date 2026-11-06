package com.example.module_product_gradle.module.order.service;

import com.example.module_product_gradle.common.constant.MessageConstant;
import com.example.module_product_gradle.common.exception.AppException;
import com.example.module_product_gradle.common.exception.ErrorCode;
import com.example.module_product_gradle.module.order.repository.OrderProcedureRepository;
import com.example.module_product_gradle.module.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProcedureRepository orderProcedureRepository;

    @Transactional
    public void placeOrderProduct(Long productId, Integer quantity) {
        try {
            orderProcedureRepository.placeOrder(productId, quantity);
        } catch (DataAccessException ex) {
            String errorMessage = MessageConstant.ORDER_PLACE_FAILED;
            Throwable cause = ex;

            while (cause != null) {
                if (cause instanceof PSQLException pgException) {
                    ServerErrorMessage serverError = pgException.getServerErrorMessage();
                    if (serverError != null && serverError.getMessage() != null) {
                        errorMessage = serverError.getMessage();
                    } else if (pgException.getMessage() != null) {
                        errorMessage = pgException.getMessage();
                    }
                    break;
                }

                cause = cause.getCause();
            }

            throw new AppException(ErrorCode.BAD_REQUEST, errorMessage);
        }
    }

    @Transactional
    public void placeTwoOrderProduct(Long productId_1, Integer quantity_1, Long productId_2, Integer quantity_2) {
        try {
            orderProcedureRepository.placeTwoOrder(productId_1, quantity_1, productId_2, quantity_2);
        } catch (DataAccessException ex) {
            String errorMessage = MessageConstant.ORDER_PLACE_FAILED;
            Throwable cause = ex;

            while (cause != null) {
                if (cause instanceof PSQLException pgException) {
                    ServerErrorMessage serverError = pgException.getServerErrorMessage();
                    if (serverError != null && serverError.getMessage() != null) {
                        errorMessage = serverError.getMessage();
                    } else if (pgException.getMessage() != null) {
                        errorMessage = pgException.getMessage();
                    }
                    break;
                }

                cause = cause.getCause();
            }

            throw new AppException(ErrorCode.BAD_REQUEST, errorMessage);
        }
    }
}
