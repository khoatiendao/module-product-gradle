package com.example.module_product_gradle.module.category.service;

import com.example.module_product_gradle.common.CommonEnum.Status;
import com.example.module_product_gradle.common.constant.MessageConstant;
import com.example.module_product_gradle.common.dto.res.PageResponse;
import com.example.module_product_gradle.common.exception.AppException;
import com.example.module_product_gradle.common.exception.ErrorCode;
import com.example.module_product_gradle.module.category.dto.req.CategoryDtoReq;
import com.example.module_product_gradle.module.category.dto.res.CategoryDtoRes;
import com.example.module_product_gradle.module.category.entity.Category;
import com.example.module_product_gradle.module.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public PageResponse<CategoryDtoRes> list(Pageable pageable) {
        return PageResponse.from(categoryRepository.findByActiveTrueAndStatus(Status.ACTIVE, pageable),
                CategoryDtoRes::from);
    }

    public void create(CategoryDtoReq.create createDto) {
        Category category = new Category();
        category.setName(createDto.name());
        category.setStatus(createDto.status());
        categoryRepository.save(category);
    }

    public CategoryDtoRes getOne(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND, MessageConstant.CATEGORY_NOT_FOUND));

        return CategoryDtoRes.from(category);
    }

    public void update(Long id ,CategoryDtoReq.update updateDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND, MessageConstant.CATEGORY_NOT_FOUND));

        category.setName(updateDto.name());
        category.setStatus(updateDto.status());
        categoryRepository.save(category);
    }

    public void changeStatus(Long id, CategoryDtoReq.changeStatusDto statusDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND, MessageConstant.CATEGORY_NOT_FOUND));
        category.setStatus(statusDto.status());
        categoryRepository.save(category);
    }

    public void softDelete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND, MessageConstant.CATEGORY_NOT_FOUND));

        category.setActive(false);
        categoryRepository.save(category);
    }
}
