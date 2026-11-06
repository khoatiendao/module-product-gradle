package com.example.module_product_gradle.module.order.controller;

import com.example.module_product_gradle.common.dto.res.ApiResponse;
import com.example.module_product_gradle.module.order.dto.req.OrderReqDto;
import com.example.module_product_gradle.module.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{productId}/place-order")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> placeOrder(
            @PathVariable Long productId,
            @Valid @RequestBody OrderReqDto.placeOrderDto dto
    ) {
        orderService.placeOrderProduct(productId, dto.quantity());
        return ApiResponse.ok("Place order success", null);
    }

    @PostMapping("/place-two-order")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> placeTwoOrder(
            @Valid @RequestBody OrderReqDto.placeTwoOrderDto dto
    ) {
        orderService.placeTwoOrderProduct(dto.productId_1(), dto.quantity_1(), dto.productId_2(), dto.quantity_2());
        return ApiResponse.ok("Place two order success", null);
    }
}
