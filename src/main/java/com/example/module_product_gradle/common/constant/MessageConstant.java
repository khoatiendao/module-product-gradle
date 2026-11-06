package com.example.module_product_gradle.common.constant;

public final class MessageConstant {
    private MessageConstant() {}

    // Common
    public static final String SUCCESS = "Success";
    public static final String FAILED = "Failed";
    public static final String INTERNAL_SERVER_ERROR = "Internal server error";
    public static final String NOT_LOGIN_OR_TOKEN_INVALID = "Token not found or token invalid";
    public static final String FORBIDDEN = "Forbidden";

    // User
    public static final String EMAIL_NOT_FOUND = "Email not found";
    public static final String EMAIL_OR_PASS_WRONG = "Email or password wrong";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exits";
    public static final String ACCOUNT_IS_DELETED = "Account is deleted";
    public static final String TOKEN_NOT_FOUND = "Token not found";

    // Product
    public static final String PRODUCT_NOT_FOUND = "Product not found";
    public static final String PRODUCT_ALREADY_EXISTS = "Product already exists";
    public static final String PRODUCT_CREATED = "Product created successfully";
    public static final String PRODUCT_UPDATED = "Product updated successfully";
    public static final String PRODUCT_DELETED = "Product deleted successfully";

    // Category
    public static final String CATEGORY_NOT_FOUND = "Category not found";
    public static final String CATEGORY_ALREADY_EXISTS = "Category already exists";
    public static final String CATEGORY_CREATED = "Category created successfully";
    public static final String CATEGORY_UPDATED = "Category updated successfully";
    public static final String CATEGORY_DELETED = "Category deleted successfully";

    // Order
    public static final String ORDER_PLACE_FAILED = "Order place failed";
}
