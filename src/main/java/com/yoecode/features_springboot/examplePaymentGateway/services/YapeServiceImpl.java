package com.yoecode.features_springboot.examplePaymentGateway.services;

import com.yoecode.features_springboot.core.exceptions.CustomException;
import com.yoecode.features_springboot.core.models.MercadoPagoProperties;
import com.yoecode.features_springboot.examplePaymentGateway.models.ChargePaymentPayerRequest;
import com.yoecode.features_springboot.examplePaymentGateway.models.ChargePaymentRequest;
import com.yoecode.features_springboot.examplePaymentGateway.models.ChargePaymentRequestDto;
import com.yoecode.features_springboot.examplePaymentGateway.models.ChargePaymentResponse;
import com.yoecode.features_springboot.examplePaymentGateway.models.YapeGenerateTokenRequest;
import com.yoecode.features_springboot.examplePaymentGateway.models.YapeGenerateTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.UUID;



@Service
public class YapeServiceImpl implements  YapeService {

    private final  MercadoPagoProperties mercadoPagoProperties;

    private static final Logger logger = LoggerFactory.getLogger(YapeServiceImpl.class);

    @Autowired
    private WebClient webClient;

    @Autowired
    public YapeServiceImpl(MercadoPagoProperties mercadoPagoProperties) {
        this.mercadoPagoProperties = mercadoPagoProperties;
    }

    @Override
    public Mono<YapeGenerateTokenResponse> generateToken(YapeGenerateTokenRequest body) {

            String requestId = UUID.randomUUID().toString();

            YapeGenerateTokenRequest request = YapeGenerateTokenRequest.builder()
                    .otp(body.getOtp())
                    .phoneNumber(body.getPhoneNumber())
                    .request_id(requestId)
                    .build();

            return webClient.post()
                    .uri("https://api.mercadopago.com/platforms/pci/yape/v1/payment?public_key=" + mercadoPagoProperties.getPublicKey())
                    .body(Mono.just(request), YapeGenerateTokenRequest.class)
                    .retrieve()
                    .bodyToMono(YapeGenerateTokenResponse.class)
                    .onErrorMap(WebClientResponseException.class, e -> {
                        logger.error("Error generate token: {}", e.getMessage());
                        return new CustomException("Ocurrio un problema al generar el token",e.getStatusCode().value());
                    });
    }

    @Override
    public Mono<ChargePaymentResponse> chargePayment(ChargePaymentRequestDto body) {
        ChargePaymentRequest request = ChargePaymentRequest.builder()
                .token(body.getToken())
                .payer(ChargePaymentPayerRequest.builder().email(body.getEmail()).build())
                .payment_method_id("yape")
                .installments(1)
                .description(body.getDescription())
                .transaction_amount(body.getAmount())
                .build();
        return webClient.post()
                .uri("https://api.mercadopago.com/v1/payments")
                .body(Mono.just(request), ChargePaymentRequest.class)
                .retrieve()
                .bodyToMono(ChargePaymentResponse.class)
                .onErrorMap(WebClientResponseException.class, e -> {
                    logger.error("Error charge pay: {}", e.getMessage());
                    return new CustomException("Ocurrio un problema al realizar el pago",e.getStatusCode().value());
                });
    }
}
