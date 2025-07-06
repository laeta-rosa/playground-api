package org.mtech.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Playground API",
                description = "API for managing kids and playsites in a playground."
        )
)
public class OpenApiConfig {}
