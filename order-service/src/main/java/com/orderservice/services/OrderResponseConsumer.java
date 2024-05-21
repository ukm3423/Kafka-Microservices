package com.orderservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.basedomain.dto.OrderEvent;

@Service
public class OrderResponseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderResponseConsumer.class);

    @KafkaListener(topics = "${spring.kafka.response-topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeResponse(OrderEvent event) {
        LOGGER.info(String.format("Response event received in order service => %s", event.toString()));

        // Handle the response event (e.g., update the order status in the database)
        System.out.println("\n\n"+event+"\n\n");
    }

    // Example method to handle updating order status in your database
    private void updateOrderStatus(OrderEvent event) {
        // Your logic to update the order status in the database
        // e.g., orderRepository.updateStatus(event.getOrder().getOrderId(),
        // event.getStatus());
    }
}
