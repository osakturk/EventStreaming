package com.event.stream.constant;

public class Constants {

    private Constants(){}
    public static final String PRODUCTS = "products";
    public static final String ORDER_SUCCESSFUL_MESSAGE = "Order successfully created. Your order id: %s";
    public static final String ARTICLE_CREATION_MESSAGE = "Articles created successfully.";
    public static final String ARTICLE_EXIST_MESSAGE = "Article already exist.";
    public static final String PRODUCTS_CREATION_MESSAGE = "Products created successfully.";

    // ERROR MESSAGES
    public static final String ORDER_NOT_FOUND_MESSAGE = "Order doesn't exist. Please check your order id.";
    public static final String PRODUCT_NOT_FOUND_MESSAGE = "Product doesn't exist. Please check product id.";
    public static final String ARTICLE_NOT_FOUND_MESSAGE = "Article doesn't exist. Please check article id.";
    public static final String ENOUGH_MATERIAL_NOT_FOUND_MESSAGE = "We apologize for shouting this out loud, but we don't have enough material.";

}
