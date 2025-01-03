package com.yoecode.features_springboot.core.exceptions;

import com.yoecode.features_springboot.core.models.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.net.ConnectException;
import java.util.concurrent.TimeoutException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.valueOf(ex.getStatusCode()).value(),
                System.currentTimeMillis(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                "An unexpected error occurred.",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                System.currentTimeMillis(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ExchangeFilterFunction handleErrors() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().is4xxClientError() || clientResponse.statusCode().is5xxServerError()) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(body -> {
                            // Mapear la respuesta de error HTTP a una excepción personalizada
                            throw new WebClientResponseException(
                                    "WebClient error: " + body,
                                    clientResponse.statusCode().value(),
                                    clientResponse.statusCode().getClass().getName(),
                                    clientResponse.headers().asHttpHeaders(),
                                    null,
                                    null
                            );
                        });
            }
            return Mono.just(clientResponse);
        });
    }

}


