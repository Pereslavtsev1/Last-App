package org.example.notificationservice.models;


import lombok.*;
import org.example.notificationservice.kafka.order.OrderConfirmation;
import org.example.notificationservice.kafka.payment.PaymentConfirmation;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Notification {
    private String id;
    private NotificationType type;
    private LocalDateTime timestamp;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;

}
