package com.ecommerce.order.controller;

import com.ecommerce.order.model.OrderRequest;
import com.ecommerce.order.model.OrderResponse;
import com.ecommerce.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest, @RequestHeader("emailId") String emailId){

        LOGGER.info(" : emailId : " + emailId + " : orderRequest :" + orderRequest);

        OrderResponse orderResponse = orderService.placeOrder(orderRequest,emailId);
        return ResponseEntity.ok(orderResponse);
    }
}