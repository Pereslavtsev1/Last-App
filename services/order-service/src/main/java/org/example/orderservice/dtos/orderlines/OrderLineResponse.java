package org.example.orderservice.dtos.orderlines;

import lombok.Builder;

@Builder
public record OrderLineResponse(
        Long id,
        double quantity
) { }