package com.bank.profile.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация OpenAPI для микросервиса профилей.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Настройка информации о сервисе в Swagger UI.
     *
     * @return объект OpenAPI
     */
    @Bean
    public OpenAPI profileOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Profile Service API")
                        .description("REST API для управления банковскими профилями")
                        .version("1.0.0"));
    }
}