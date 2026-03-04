package com.aceleradev.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "NexOrder API",
                version = "v1.0.0",
                description = "REST API for uploading, listing, downloading and deleting stored files.",
                contact = @Contact(
                        name = "Nícollas",
                        email = "nicollaswendel1623@gmail.com"
                ),
                license = @License(
                        name = "MIT License"
                )
        ))
public class OpenApiConfig {}

