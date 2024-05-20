package com.emailservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.basedomain.dto.OrderEvent;

@Service
public class OrderConsumer {
    

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId="${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent event){

        LOGGER.info(String.format("Order event received in email service => %s ", event.toString()));



        // send email to the customer 

        System.out.println("\n\n"+event+"\n\n");
        

        // Order order = new Order(); 
        // order.setName(event.getOrder().getName());
        // order.setOrderId(event.getOrder().getOrderId());
        // order.setPrice(event.getOrder().getPrice());
        // order.setQuantity(event.getOrder().getQuantity());

        // OrderEvent orderEventEntity = new OrderEvent();
        
        // orderEventEntity.setOrder(order);
        // orderEventEntity.setStatus(event.getStatus());
        // orderEventEntity.setMessage(event.getMessage());

        // System.out.println(orderEventEntity);

    }


}
