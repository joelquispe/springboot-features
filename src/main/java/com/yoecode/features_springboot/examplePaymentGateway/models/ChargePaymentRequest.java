package com.yoecode.features_springboot.examplePaymentGateway.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargePaymentRequest {
    private String token;
    private double transaction_amount;
    private String description;
    private int installments;
    private String payment_method_id;
    private ChargePaymentPayerRequest payer;
}


