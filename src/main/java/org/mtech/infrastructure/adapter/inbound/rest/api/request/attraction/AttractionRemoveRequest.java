package org.mtech.infrastructure.adapter.inbound.rest.api.request.attraction;

import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.validation.annotation.Validated;

@Validated
public record AttractionRemoveRequest(
         @PositiveOrZero Long id
) {}
