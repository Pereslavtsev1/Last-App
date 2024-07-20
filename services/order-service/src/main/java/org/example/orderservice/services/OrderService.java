package org.example.orderservice.services;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.customer.CustomerClient;
import org.example.orderservice.dtos.order.OrderRequest;
import org.example.orderservice.dtos.order.OrderResponse;
import org.example.orderservice.dtos.orderlines.OrderLineRequest;
import org.example.orderservice.exceptions.CustomerNotFoundException;
import org.example.orderservice.exceptions.OrderNotFoundException;
import org.example.orderservice.mappers.OrderMapper;
import org.example.orderservice.orderProducer.OrderProducer;
import org.example.orderservice.orderProducer.dtos.OrderConfirmation;
import org.example.orderservice.payment.PaymentClient;
import org.example.orderservice.payment.PaymentRequest;
import org.example.orderservice.product.dtos.ProductClient;
import org.example.orderservice.repositories.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Long createOrder(OrderRequest request) {
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with id: '%s' not found", request.customerId()), HttpStatus.BAD_REQUEST
                ));
        var purchaseProducts = productClient.purchaseProduct(request.products());
        var order = orderRepository.save(orderMapper.toOrder(request));
        for (var purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    OrderLineRequest.builder()
                            .id(null)
                            .orderId(order.getId())
                            .productId(purchaseRequest.productId())
                            .quantity(purchaseRequest.quantity())
                            .build()
            );
        }

        paymentClient.payment(new PaymentRequest(
                request.totalAmount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer));

        orderProducer.sendOrderConfirmation(OrderConfirmation.builder()
                .orderReference(request.reference())
                .paymentMethod(request.paymentMethod())
                .totalAmount(request.totalAmount())
                .customer(customer)
                .products(purchaseProducts)
                .build());
        return order.getId();
    }

    public OrderResponse findById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::toOrderResponse)
                .orElseThrow(() -> new OrderNotFoundException(
                        String.format("Order with id: '%s' not found", orderId), HttpStatus.BAD_REQUEST
                ));


    }
}
