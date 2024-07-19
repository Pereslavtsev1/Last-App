package org.example.productservice.mappers;

import org.example.productservice.dtos.ProductPurchaseResponse;
import org.example.productservice.dtos.ProductRequest;
import org.example.productservice.dtos.ProductResponse;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest product) {
        return Product.builder()
                .name(product.name())
                .price(product.price())
                .id(product.id())
                .availableQuantity(product.availableQuantity())
                .category(
                        Category.builder().id(product.categoryId()).build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .availableQuantity(product.getAvailableQuantity())
                    .description(product.getDescription())
                    .categoryDescription(product.getCategory().getDescription())
                    .categoryName(product.getCategory().getName())
                    .categoryId(product.getCategory().getId())
                    .build();
    }
    public ProductPurchaseResponse toproductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
