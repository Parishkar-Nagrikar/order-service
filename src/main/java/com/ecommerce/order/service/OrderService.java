package com.ecommerce.order.service;

import com.ecommerce.order.domain.Order;
import com.ecommerce.order.model.*;
import com.ecommerce.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestTemplate restTemplate;

    public OrderResponse placeOrder(OrderRequest orderRequest,String emailId){

        ProductResponse productResponse = getProductDetails(orderRequest.getProductId());

        OrderResponse orderResponse = new OrderResponse();

        UserResponse userResponse = getUserDetails(emailId);

//product response to order response
        if(productResponse.getQuantity() >= orderRequest.getQuantity()){

            Order newOrder = orderRepository.save(orderRequestMapper(orderRequest,emailId,userResponse));
            //map new order into response
            orderResponse = orderResponseMapper(newOrder);
            orderResponse.setMessage("");
        }else {
            orderResponse.setMessage("order not placed!");
        }
        return orderResponse;
    }

//call product service
    public ProductResponse getProductDetails(Integer productId){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);

        HttpEntity<ProductResponse> response = restTemplate.exchange("http://localhost:9092/product/findProduct/"+productId, HttpMethod.GET, entity, ProductResponse.class);

        //TODO : fix in case of null
        ProductResponse productResponse = response.getBody();


        return productResponse;
    }


//call product service
    public UserResponse getUserDetails(String emailId){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);

        HttpEntity<UserResponse> response = restTemplate.exchange("http://localhost:9091/user/email/"+emailId, HttpMethod.GET, entity, UserResponse.class);

        UserResponse userResponse = response.getBody();

        return userResponse;
    }

//domain to model
    private OrderResponse orderResponseMapper(Order order) {
        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setQuantity(order.getQuantity());
        orderResponse.setProductId(order.getProductId());
        orderResponse.setEmailId(order.getEmailId());
        orderResponse.setOrderId(order.getOrderId());
        orderResponse.setAddress(order.getAddress());

        return orderResponse;
    }

//model to domain
    private Order orderRequestMapper(OrderRequest orderRequest,String emailId,UserResponse userResponse) {
        Order order = new Order();

        order.setEmailId(emailId);
        order.setProductId(orderRequest.getProductId());
        order.setQuantity(orderRequest.getQuantity());
        order.setAddress(userResponse.getAddress());
        return order;
    }
}