package org.example.customerservice.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.customerservice.models.Address;


public record CustomerRequest(
        String id,
        @NotBlank(message = "First name must not be blank")
        @NotNull(message = "First name cannot be empty")
        @Size(min = 2, max = 50, message = "First name length must be between 2 and 50")
        String firstName,
        @NotBlank(message = "Last name must not be blank")
        @NotNull(message = "Last name cannot be empty")
        @Size(min = 2, max = 50, message = "Last name length must be between 2 and 50")
        String lastName,
        @NotBlank(message = "Email cannot be empty")
        @Email
        String email,
        Address address
) {
}