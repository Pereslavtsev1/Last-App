package org.example.paymentservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaPaymentTopic {
    @Bean
    public NewTopic orderTopic() {
        return TopicBuilder.name("payment-config").build();
    }
}
