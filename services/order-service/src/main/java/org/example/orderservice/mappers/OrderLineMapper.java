package org.example.orderservice.mappers;

import org.example.orderservice.dtos.orderlines.OrderLineRequest;
import org.example.orderservice.dtos.orderlines.OrderLineResponse;
import org.example.orderservice.modles.Order;
import org.example.orderservice.modles.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .productId(request.productId())
                .order(Order.builder()
                        .id(request.orderId())
                        .build())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return OrderLineResponse.builder()
                .id(orderLine.getId())
                .quantity(orderLine.getQuantity())
                .build();
    }
}
