package org.example.orderservice.dtos.orderlines;

import lombok.Builder;

@Builder
public record OrderLineRequest(
        Long id,
        Long orderId,
        Long productId,
        double quantity
){
}
