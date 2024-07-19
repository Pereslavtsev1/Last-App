package org.example.productservice.exceptions;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends BaseException {
    public ProductNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
