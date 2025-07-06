package org.mtech.infrastructure.adapter.inbound.rest.api.request.attraction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.mtech.infrastructure.adapter.inbound.rest.validation.ValidAttraction;
import org.springframework.validation.annotation.Validated;

@Validated
public record AttractionAddRequest(
         @NotNull RequestAttraction attraction
) {

    @Validated
    public record RequestAttraction(
            @NotNull @ValidAttraction String name,
            @Positive Integer count
    ) {}

}
