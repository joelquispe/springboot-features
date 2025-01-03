package com.yoecode.features_springboot.examplePaymentGateway.services;

import com.yoecode.features_springboot.examplePaymentGateway.models.ChargePaymentRequest;
import com.yoecode.features_springboot.examplePaymentGateway.models.ChargePaymentRequestDto;
import com.yoecode.features_springboot.examplePaymentGateway.models.ChargePaymentResponse;
import com.yoecode.features_springboot.examplePaymentGateway.models.YapeGenerateTokenRequest;
import com.yoecode.features_springboot.examplePaymentGateway.models.YapeGenerateTokenResponse;
import reactor.core.publisher.Mono;

public interface YapeService {
    Mono<YapeGenerateTokenResponse> generateToken(YapeGenerateTokenRequest body);
    Mono<ChargePaymentResponse> chargePayment(ChargePaymentRequestDto body);
}

