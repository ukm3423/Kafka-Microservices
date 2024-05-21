package com.stockservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.basedomain.dto.OrderEvent;

@Service
public class OrderResponseProducer {
    

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderResponseProducer.class);

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    // @Value("${spring.kafka.response-topic.name}")
    // private String responseTopicName;

    @Autowired
    private NewTopic responseTopicName;

    // @Value("${spring.kafka.response-topic.name}")
    // private String responseTopicName;
    

    public void sendResponse(OrderEvent event) {
        LOGGER.info(String.format("Sending order response => %s", event.toString()));

        Message<OrderEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, responseTopicName.name())
                .build();

        kafkaTemplate.send(message);
    }

}
