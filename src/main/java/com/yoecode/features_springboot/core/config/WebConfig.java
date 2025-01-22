package com.yoecode.features_springboot.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configurar para servir archivos de la carpeta uploads
        registry.addResourceHandler("/uploads/**")  // URL de acceso
                .addResourceLocations("file:uploads/");  // Ruta del sistema de archivos

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permitir solicitudes desde cualquier origen
        registry.addMapping("/**")
                .allowedOrigins("*")  // Cambia esto a la URL de tu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
