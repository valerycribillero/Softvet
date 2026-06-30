package com.recipe.recipe_service.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI examOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Softvet")
                        .description("Registro de examenes de mascotas")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Valery")
                                .email("va.cribillero@duocuc.cl")
                        )
                );
    }
}
