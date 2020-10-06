package com.ecommerce.order.domain;

import com.ecommerce.order.model.Address;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document("order")
public class Order {

    @Getter@Setter
    String id;

    @Indexed(unique = true)
    @Getter @Setter
    private String orderId = UUID.randomUUID().toString();

    @Getter@Setter
    Integer productId;

    @Getter@Setter
    Integer quantity;

    @Getter@Setter
    String emailId;

    @Getter
    @Setter
    Address address;
}
