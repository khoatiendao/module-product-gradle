package com.example.module_product_gradle.module.product.controller;

import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.module_product_gradle.common.dto.res.ApiResponse;
import com.example.module_product_gradle.common.dto.res.PageResponse;
import com.example.module_product_gradle.module.product.dto.req.ProductDtoReq;
import com.example.module_product_gradle.module.product.dto.res.ProductDtoRes;
import com.example.module_product_gradle.module.product.service.ProductService;
import org.springframework.data.domain.Sort;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<ProductDtoRes> getAll(@ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return productService.list(pageable);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> create(@Valid @RequestBody ProductDtoReq.Create createDto) {
        productService.createProduct(createDto);
        return ApiResponse.ok("Created", null);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ProductDtoRes> getOne(@PathVariable Long id) {
        return ApiResponse.ok(productService.getOneProduct(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> update(@PathVariable Long id, @Valid @RequestBody ProductDtoReq.Update updateDto) {
        productService.updateProduct(id, updateDto);
        return ApiResponse.ok("Updated", null);
    }

    @PatchMapping("/{id}/remove-quantity")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Integer> removeQuantity(@PathVariable Long id) {
        return ApiResponse.ok("Remove", productService.removeQuantityProduct(id));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        productService.softDeleteProduct(id);
        return ApiResponse.ok("Deleted", null);
    }
}
