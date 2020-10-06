package com.ecommerce.order.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.DeleteMapping;

@Data
public class OrderRequest {

    @Getter@Setter
    Integer productId;

    @Getter@Setter
    Integer quantity;
}

