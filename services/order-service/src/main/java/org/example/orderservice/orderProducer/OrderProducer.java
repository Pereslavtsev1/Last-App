package org.example.orderservice.orderProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orderservice.orderProducer.dtos.OrderConfirmation;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {
    private KafkaTemplate<String, OrderConfirmation> kafkaTemplate;
    public void sendOrderConfirmation(OrderConfirmation orderConfirmation){
        Message<OrderConfirmation> message = MessageBuilder.withPayload(orderConfirmation)
                .setHeader("kafka-topic", "order-topic")
                .build();
        log.info("Sending message to order-topic");
        kafkaTemplate.send(message);
    }
}
