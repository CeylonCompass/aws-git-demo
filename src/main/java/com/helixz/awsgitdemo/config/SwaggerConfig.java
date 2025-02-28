package com.helixz.awsgitdemo.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("AWS Git Demo")
                        .description("AWS Git Demo API")
                        .version("1.0"));
    }
}
