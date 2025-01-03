package com.yoecode.features_springboot.examplePaymentGateway.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChargePaymentPayerRequest {
    private String email;
}
