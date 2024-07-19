package org.example.orderservice.services;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.dtos.orderlines.OrderLineRequest;
import org.example.orderservice.dtos.orderlines.OrderLineResponse;
import org.example.orderservice.mappers.OrderLineMapper;
import org.example.orderservice.repositories.OrderLineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;
    public Long saveOrderLine(OrderLineRequest request) {
         return orderLineRepository.save(orderLineMapper.toOrderLine(request)).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Long orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::toOrderLineResponse)
                .toList();
    }
}
