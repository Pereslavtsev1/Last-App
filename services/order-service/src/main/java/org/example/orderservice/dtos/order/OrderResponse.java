package org.example.orderservice.dtos.order;

import lombok.Builder;
import org.example.orderservice.paymentMethods.PaymentMethod;

import java.math.BigDecimal;

@Builder
public record OrderResponse(
        Long id,
        String reference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        String customerId
) {
}
