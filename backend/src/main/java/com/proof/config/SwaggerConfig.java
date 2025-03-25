package com.proof.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de Swagger para la documentación de la API.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión Académica")
                        .version("1.0.0")
                        .description(
                                "Documentación de la API para la gestión de personas (estudiantes, profesores, administrativos), cursos e inscripciones.")
                        .contact(new Contact()
                                .name("David Orlando Velez Zamora")
                                .email("dovz1999@gmail.com")
                                .url("https://github.com/DavidZam09")));
    }
}
