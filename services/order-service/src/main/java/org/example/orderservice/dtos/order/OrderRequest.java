package org.example.orderservice.dtos.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.orderservice.paymentMethods.PaymentMethod;
import org.example.orderservice.productPurchase.dtos.ProductPurchaseResponse;

import java.math.BigDecimal;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record OrderRequest(
        Long id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal totalAmount,
        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer should be present")
        @NotEmpty(message = "Customer should be present")
        @NotBlank(message = "Customer should be present")
        String customerId,
        @NotEmpty(message = "You should at least purchase one product")
        List<ProductPurchaseResponse> products
) {
}
