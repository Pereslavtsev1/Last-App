package org.example.customerservice.dtos;


import lombok.Builder;
import org.example.customerservice.models.Address;

@Builder
public record CustomerResponse (
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
