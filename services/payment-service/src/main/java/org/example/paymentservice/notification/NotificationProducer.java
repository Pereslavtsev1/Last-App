package org.example.paymentservice.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor

public class NotificationProducer {

    private final KafkaTemplate<String, PaymentNotification> kafkaTemplate;

    public void sendNotification(PaymentNotification paymentNotification) {
        kafkaTemplate.send(MessageBuilder.withPayload(paymentNotification)
                .setHeader(KafkaHeaders.TOPIC,"payment-topic").build());
        log.info("Notification sent to payment topic");
    }
}
