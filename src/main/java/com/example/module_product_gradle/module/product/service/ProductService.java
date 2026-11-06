package com.example.module_product_gradle.module.product.service;

import com.example.module_product_gradle.common.dto.res.ApiResponse;
import com.example.module_product_gradle.module.category.entity.Category;
import com.example.module_product_gradle.module.category.repository.CategoryRepository;
import com.example.module_product_gradle.module.category.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.module_product_gradle.common.constant.MessageConstant;
import com.example.module_product_gradle.common.dto.res.PageResponse;
import com.example.module_product_gradle.common.exception.AppException;
import com.example.module_product_gradle.common.exception.ErrorCode;
import com.example.module_product_gradle.module.product.dto.req.ProductDtoReq;
import com.example.module_product_gradle.module.product.dto.res.ProductDtoRes;
import com.example.module_product_gradle.module.product.entity.Product;
import com.example.module_product_gradle.module.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public PageResponse<ProductDtoRes> list(Pageable pageable) {
        return PageResponse.from(productRepository.findByActiveTrue(pageable), ProductDtoRes::from);
    }

    public void createProduct(ProductDtoReq.Create createDto) {
        Category category = categoryRepository.findById(createDto.categoryId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND, MessageConstant.CATEGORY_NOT_FOUND));
        Product product = new Product();
        product.setName(createDto.name());
        product.setPrice(createDto.price());
        product.setQuantity(createDto.quantity());
        product.setCategory(category);
        productRepository.save(product);
    }

    public ProductDtoRes getOneProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND, MessageConstant.PRODUCT_NOT_FOUND));
        
        if(product.getActive() == false) {
            throw new AppException(ErrorCode.NOT_FOUND, MessageConstant.PRODUCT_NOT_FOUND);
        }

        return ProductDtoRes.from(product);
    }

    public void updateProduct(Long id, ProductDtoReq.Update updateDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND, MessageConstant.PRODUCT_NOT_FOUND));

        Category category = categoryRepository.findById(updateDto.categoryId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND, MessageConstant.CATEGORY_NOT_FOUND));
        
        product.setName(updateDto.name());
        product.setPrice(updateDto.price());
        product.setQuantity(updateDto.quantity());
        product.setCategory(category);
        productRepository.save(product);
    }

    @Transactional
    public Integer removeQuantityProduct(Long id) {
        Integer findAndRemove = productRepository.removeQuantity(id);

        if(findAndRemove == null) {
            throw new AppException(ErrorCode.NOT_FOUND, MessageConstant.PRODUCT_NOT_FOUND);
        }

        return findAndRemove;
    }

    public void softDeleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND, MessageConstant.PRODUCT_NOT_FOUND));

        product.setActive(false);
        productRepository.save(product);
    }
}
