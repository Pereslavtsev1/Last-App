package org.example.orderservice.exceptions.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
