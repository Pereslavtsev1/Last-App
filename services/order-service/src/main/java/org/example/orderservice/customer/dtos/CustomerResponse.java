package org.example.orderservice.customer.dtos;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
