package com.aceleradev.nexorder.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "NexOrder API",
                version = "v1.0.0",
                description = "REST API for managing orders, products and payments, including customer and employee management.",
                license = @License(
                        name = "MIT License"
                )
        ))
public class OpenApiConfig {}
