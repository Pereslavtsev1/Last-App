package org.example.orderservice.payment;

import org.example.orderservice.customer.dtos.CustomerResponse;
import org.example.orderservice.paymentMethods.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,
        CustomerResponse customer
) {
}
