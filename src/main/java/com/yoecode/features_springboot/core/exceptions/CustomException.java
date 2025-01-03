package com.yoecode.features_springboot.core.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    private final int statusCode;

    // Constructor con solo el mensaje, utilizando BAD_REQUEST como valor predeterminado
    public CustomException(String message) {
        super(message);
        this.statusCode = HttpStatus.BAD_REQUEST.value();  // Valor por defecto
    }

    // Constructor con mensaje y código de estado
    public CustomException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    // Getter para obtener el código de estado
    public int getStatusCode() {
        return statusCode;
    }
}