package org.example.productservice.dtos;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponse(
        Long id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        Long categoryId,
        String categoryName,
        String categoryDescription
) {
}
