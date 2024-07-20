package org.example.paymentservice.mappers;

import org.example.paymentservice.dtos.PaymentRequest;
import org.example.paymentservice.models.Payment;
import org.example.paymentservice.notification.PaymentNotification;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .orderId(request.orderId())
                .paymentMethod(request.paymentMethod())
                .totalAmount(request.totalAmount())
                .build();
    }


    public PaymentNotification toPaymentNotification(PaymentRequest request) {
        return PaymentNotification.builder()
                .paymentMethod(request.paymentMethod())
                .totalAmount(request.totalAmount())
                .customerFirstName(request.customer().firstname())
                .customerLastName(request.customer().lastname())
                .orderReference(request.orderReference())
                .customerEmail(request.customer().email())
                .build();
    }
}
