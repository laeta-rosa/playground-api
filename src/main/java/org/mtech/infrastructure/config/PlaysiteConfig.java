package org.mtech.infrastructure.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("playsite")
public record PlaysiteConfig(
        @NotNull Queue queue,
        @NotNull Attractions attractions
) {

    public record Queue(
            @NotNull Integer size
    ) {}

    public record Attractions(
            @NotNull Integer maxCount
    ) {}

}
