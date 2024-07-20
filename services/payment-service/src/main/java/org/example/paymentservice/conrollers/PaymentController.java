package org.example.paymentservice.conrollers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.paymentservice.dtos.PaymentRequest;
import org.example.paymentservice.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> payment(@Valid @RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.creatPayment(request));
    }
}
