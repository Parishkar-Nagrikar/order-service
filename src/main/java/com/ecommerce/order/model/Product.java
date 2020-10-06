package com.ecommerce.order.model;


import lombok.Getter;
import lombok.Setter;

public class Product {

    @Getter @Setter
    Integer productId;

    @Getter@Setter
    String name;

    @Getter@Setter
    String description;

    @Getter@Setter
    Double price;

    @Getter@Setter
    String currencyCode;

    @Getter@Setter
    Integer quantity;
}
