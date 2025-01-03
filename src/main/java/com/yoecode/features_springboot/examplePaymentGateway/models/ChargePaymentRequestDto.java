package com.yoecode.features_springboot.examplePaymentGateway.models;

import lombok.Data;

@Data
public class ChargePaymentRequestDto {
    private String token;
    private int amount;
    private String description;
    private String email;
}


