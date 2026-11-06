package com.example.module_product_gradle.module.category.controller;

import com.example.module_product_gradle.common.dto.res.ApiResponse;
import com.example.module_product_gradle.common.dto.res.PageResponse;
import com.example.module_product_gradle.module.category.dto.req.CategoryDtoReq;
import com.example.module_product_gradle.module.category.dto.res.CategoryDtoRes;
import com.example.module_product_gradle.module.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<CategoryDtoRes> getList(
            @ParameterObject
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return categoryService.list(pageable);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> createCategory(@Valid @RequestBody CategoryDtoReq.create createDto) {
        categoryService.create(createDto);
        return ApiResponse.ok("Created", null);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<CategoryDtoRes> findOne(@PathVariable Long id) {
        return ApiResponse.ok(categoryService.getOne(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> updateCategory(@PathVariable Long id,@Valid @RequestBody CategoryDtoReq.update updateDto) {
        categoryService.update(id, updateDto);
        return ApiResponse.ok("Updated", null);
    }

    @PutMapping("/update-status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Void> updateStatus(@PathVariable Long id, @Valid @RequestBody CategoryDtoReq.changeStatusDto updateStatusDto) {
        categoryService.changeStatus(id, updateStatusDto);
        return ApiResponse.ok("Updated", null);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> softDeleteCategory(@PathVariable Long id) {
        categoryService.softDelete(id);
        return ApiResponse.ok("Deleted", null);
    }

}
