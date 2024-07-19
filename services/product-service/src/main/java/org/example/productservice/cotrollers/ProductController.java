package org.example.productservice.cotrollers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.productservice.dtos.ProductPurchaseRequest;
import org.example.productservice.dtos.ProductPurchaseResponse;
import org.example.productservice.dtos.ProductRequest;
import org.example.productservice.dtos.ProductResponse;
import org.example.productservice.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Long> createProduct(@Valid @RequestBody ProductRequest product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct
            (@Valid @RequestBody List<ProductPurchaseRequest> purchaseRequests) {
        return ResponseEntity.ok(productService.productPurchase(purchaseRequests));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("product-id") Long productId){
        return ResponseEntity.ok(productService.findProductById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }
}
