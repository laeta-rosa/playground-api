package org.mtech.infrastructure.adapter.inbound.rest.api.request.playsite;

import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.validation.annotation.Validated;

@Validated
public record PlaysiteRemoveRequest(
         @PositiveOrZero Long id
) {}
