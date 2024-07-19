package org.example.orderservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.orderservice.dtos.order.OrderRequest;
import org.example.orderservice.dtos.order.OrderResponse;
import org.example.orderservice.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Long> createOrder(@Valid @RequestBody OrderRequest order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findOrderById(@PathVariable("order-id") Long orderId) {
        return ResponseEntity.ok(orderService.findById(orderId));
    }
}
