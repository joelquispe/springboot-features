package com.yoecode.features_springboot.examplePaymentGateway.models;

import lombok.Data;

@Data
public class ChargePaymentResponse {
    private String status;
    private String status_detail;
    private String transaction_amount;
    private String id;

}
