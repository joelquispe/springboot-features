package com.yoecode.features_springboot.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Pagos con PayPal")
                        .version("1.0")
                        .description("Documentación de la API para integración con PayPal")
                        .contact(new Contact().name("Joel").email("tu-email@ejemplo.com"))
                        .license(new License().name("MIT").url("https://opensource.org/licenses/MIT")));
    }
}