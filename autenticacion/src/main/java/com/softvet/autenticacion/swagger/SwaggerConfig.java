package com.softvet.autenticacion.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI registroOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Softvet")
                        .description("Registro de nuevos usuarios a la web")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Karla")
                                .email("ka.ferrada@duocuc.cl")
                        )
                );
    }
}
