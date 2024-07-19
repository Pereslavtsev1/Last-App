package org.example.productservice.exceptions;

import org.springframework.http.HttpStatus;

public class ProductPurchaseException extends BaseException {
    public ProductPurchaseException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
