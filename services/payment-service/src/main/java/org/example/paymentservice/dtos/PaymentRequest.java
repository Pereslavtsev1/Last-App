package org.example.paymentservice.dtos;

import org.example.paymentservice.customer.Customer;
import org.example.paymentservice.paymentMethods.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Long id,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Long orderId,
        String orderReference,
        Customer customer
) {
}