package org.example.paymentservice.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.paymentservice.dtos.PaymentRequest;
import org.example.paymentservice.mappers.PaymentMapper;

import org.example.paymentservice.notification.NotificationProducer;
import org.example.paymentservice.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer NotificationProducer;
    private final NotificationProducer notificationProducer;

    @Transactional()
    public Long createPayment(PaymentRequest request) {
        var payment = paymentRepository.save(paymentMapper.toPayment(request));
        notificationProducer.sendNotification(paymentMapper.toPaymentNotification(request));
        return payment.getId();
    }
}
