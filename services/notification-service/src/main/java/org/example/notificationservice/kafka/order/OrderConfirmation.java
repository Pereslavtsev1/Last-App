package org.example.notificationservice.kafka.order;

import org.example.notificationservice.kafka.customer.CustomerResponse;
import org.example.notificationservice.kafka.order.payment.PaymentMethod;
import org.example.notificationservice.kafka.product.ProductPurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record
OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<ProductPurchaseResponse> products
) {
}
