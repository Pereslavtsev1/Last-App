package org.example.orderservice.kafkaConfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaOrderConfig {
    @Bean
    public NewTopic orderTopic() {
        return new NewTopic("order-topic", 5, (short) 5);
    }
}
