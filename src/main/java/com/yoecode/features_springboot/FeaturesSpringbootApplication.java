package com.yoecode.features_springboot;

import com.yoecode.features_springboot.core.models.MercadoPagoProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({MercadoPagoProperties.class})
public class FeaturesSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeaturesSpringbootApplication.class, args);
	}

}
