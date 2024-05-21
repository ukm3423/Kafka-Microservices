package com.orderservice.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basedomain.dto.Order;
import com.basedomain.dto.OrderEvent;
import com.orderservice.services.OrderProducer;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderProducer orderProducer; 
    
    @PostMapping("/order")
    public Object placeOrder(@RequestBody Order order){

        order.setOrderId(UUID.randomUUID().toString());
        order.setName(order.getName());
        order.setPrice(order.getPrice());
        order.setQuantity(order.getQuantity());


        OrderEvent event = new OrderEvent(); 
        event.setStatus("PENDING");
        event.setMessage("Order status is in pending state");
        event.setOrder(order);

        orderProducer.sendMessage(event);

        return event;
    }

    // @PostMapping
    // public String createOrder(@RequestBody Order order) {
    //     // Save order to database (omitted for simplicity)
    //     kafkaTemplate.send("order-topic", order);
    //     return "Order created!";
    // }

}
