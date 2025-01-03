package com.yoecode.features_springboot.examplePaymentGateway.config;


import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder){
        return builder
                .defaultHeader("x-idempotency-key", "1234567890")
                .defaultHeader("Authorization", "Bearer TEST-7162322941745528-011821-004f47440933d24a20c5a8d8a07ec411-712374274")
                .filter(handleErrors())
                .build();
    }

    private ExchangeFilterFunction handleErrors() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().is4xxClientError() || clientResponse.statusCode().is5xxServerError()) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(body -> {
                            // Lanzar la excepción si hay un error de respuesta HTTP
                            return Mono.error(new WebClientResponseException(
                                    "WebClient error: " + body,
                                    clientResponse.statusCode().value(),
                                    clientResponse.statusCode().getClass().getName(),
                                    clientResponse.headers().asHttpHeaders(),
                                    null,
                                    null
                            ));
                        });
            }
            return Mono.just(clientResponse);
        });
    }

}
