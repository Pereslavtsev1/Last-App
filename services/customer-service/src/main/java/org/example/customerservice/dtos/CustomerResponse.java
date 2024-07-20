package org.example.customerservice.dtos;


import lombok.Builder;

@Builder
public record CustomerResponse (
        String id,
        String firstname,
        String lastname,
        String email
) {
}
