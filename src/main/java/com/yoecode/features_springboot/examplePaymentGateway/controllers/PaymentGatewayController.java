package com.yoecode.features_springboot.examplePaymentGateway.controllers;

import com.yoecode.features_springboot.examplePaymentGateway.models.ChargePaymentRequest;
import com.yoecode.features_springboot.examplePaymentGateway.models.ChargePaymentRequestDto;
import com.yoecode.features_springboot.examplePaymentGateway.models.ChargePaymentResponse;
import com.yoecode.features_springboot.examplePaymentGateway.models.YapeGenerateTokenRequest;
import com.yoecode.features_springboot.examplePaymentGateway.models.YapeGenerateTokenResponse;
import com.yoecode.features_springboot.examplePaymentGateway.services.YapeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/payment-gateway")
public class PaymentGatewayController {

    @Autowired
    private YapeService yapeService;

    @PostMapping("/generate-token")
    private Mono<ResponseEntity<YapeGenerateTokenResponse>> generateToken(@RequestBody YapeGenerateTokenRequest body) {
        return yapeService.generateToken(body)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/charge-payment")
    private Mono<ResponseEntity<ChargePaymentResponse>> chargePayment(@RequestBody ChargePaymentRequestDto body) {
        return yapeService.chargePayment(body)
                .map(ResponseEntity::ok);
    }
}
