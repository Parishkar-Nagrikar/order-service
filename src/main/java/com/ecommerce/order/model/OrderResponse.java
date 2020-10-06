package com.ecommerce.order.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class OrderResponse {

    @Getter @Setter
    String orderId;

    @Getter @Setter
    Integer productId;

    @Getter@Setter
    Integer quantity;

    @Getter@Setter
    String emailId;

    @Getter@Setter
    String message;

    @Getter
    @Setter
    Address address;
}
