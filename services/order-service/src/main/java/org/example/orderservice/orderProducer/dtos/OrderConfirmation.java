package org.example.orderservice.orderProducer.dtos;

import lombok.Builder;
import org.example.orderservice.customer.dtos.CustomerResponse;
import org.example.orderservice.paymentMethods.PaymentMethod;
import org.example.orderservice.productPurchase.dtos.ProductPurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<ProductPurchaseResponse> products

) {
}