package org.example.orderservice.exceptions;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends BaseException {
    public CustomerNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
