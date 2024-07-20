package org.example.paymentservice.notification;

import lombok.Builder;
import org.example.paymentservice.paymentMethods.PaymentMethod;

import java.math.BigDecimal;

@Builder
public record PaymentNotification(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail

) {
}
