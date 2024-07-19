package org.example.productservice.services;

import lombok.RequiredArgsConstructor;
import org.example.productservice.dtos.ProductPurchaseRequest;
import org.example.productservice.dtos.ProductPurchaseResponse;
import org.example.productservice.dtos.ProductRequest;
import org.example.productservice.dtos.ProductResponse;
import org.example.productservice.exceptions.ProductNotFoundException;
import org.example.productservice.exceptions.ProductPurchaseException;
import org.example.productservice.mappers.ProductMapper;
import org.example.productservice.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Long createProduct(ProductRequest product) {
        return productRepository.save(productMapper.toProduct(product)).getId();
    }

    public List<ProductPurchaseResponse> productPurchase(List<ProductPurchaseRequest> purchaseRequests) {
        var productsIds = purchaseRequests
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var products = productRepository.findAllByIdInOrderById(productsIds);
        if (products.size() != purchaseRequests.size()) {
            throw new ProductPurchaseException("One or more products does not exist", HttpStatus.BAD_REQUEST);
        }
        var sortedRequest = purchaseRequests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < products.size(); i++) {
            var product = products.get(i);
            var productRequest = sortedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId(),HttpStatus.BAD_REQUEST);
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toproductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;

    }

    public ProductResponse findProductById(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                String.format("Product with id: '%s' not found", productId), HttpStatus.BAD_REQUEST
                        )
                );
    }

    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .toList();
    }
}
