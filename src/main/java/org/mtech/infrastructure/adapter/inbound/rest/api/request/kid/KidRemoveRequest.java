package org.mtech.infrastructure.adapter.inbound.rest.api.request.kid;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record KidRemoveRequest(
        @NotNull String ticketNumber
) {}
