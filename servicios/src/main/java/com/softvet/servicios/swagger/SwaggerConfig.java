package com.softvet.servicios.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI registroOpenAPI() {
        return new OpenAPI()
                .info(
                    new Info()
                        .title("API Servicios")
                        .description("Microservicio de servicios veterinarios")
                        .version("1.0")
                );
    }
}