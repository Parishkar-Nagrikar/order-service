package com.ecommerce.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserResponse {

    @Getter
    @Setter
    String userId;

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    String email;

    @Getter
    @Setter
    Address address;

    @Getter
    @Setter
    String role;
}
