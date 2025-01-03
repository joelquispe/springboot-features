package com.yoecode.features_springboot.examplePaymentGateway.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YapeGenerateTokenRequest {
    private String phoneNumber;
    private String otp;
    private String request_id;
}
