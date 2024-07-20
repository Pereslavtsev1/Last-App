package org.example.orderservice.product.dtos;


import jakarta.validation.constraints.NotEmpty;
import org.example.orderservice.productPurchase.dtos.ProductPurchaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "product-client", url = "${application.config.product-url}")
public interface ProductClient {
    @PostMapping("/purchase")
    List<ProductPurchaseResponse> purchaseProduct(@NotEmpty(message = "You should at least purchase one product") List<ProductPurchaseResponse> purchaseRequests);


}
