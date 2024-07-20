package org.example.notificationservice.kafka.payment;

import lombok.Builder;
import org.example.notificationservice.kafka.order.payment.PaymentMethod;

import java.math.BigDecimal;

@Builder
public record PaymentConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
