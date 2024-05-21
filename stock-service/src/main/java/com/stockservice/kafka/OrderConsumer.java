package com.stockservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.basedomain.dto.OrderEvent;

@Service
public class OrderConsumer {
    
    @Autowired
    private OrderResponseProducer orderResponseProducer;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId="${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent event){
        LOGGER.info(String.format("Order event received in stock service => %s ", event.toString()));


        // save data to the database
        System.out.println("\n\n"+event+"\n\n");

        event.setStatus("PROCESSED");
        event.setMessage("Order has been processed in stock service");
        // Send response to order-service
        orderResponseProducer.sendResponse(event);

        



    }


}
