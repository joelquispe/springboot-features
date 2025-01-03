package com.yoecode.features_springboot.core.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "mercado-pago")
public class MercadoPagoProperties {
    private String publicKey;
}
