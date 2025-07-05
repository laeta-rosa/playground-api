package org.mtech.adapter.inbound.rest.request;

import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.validation.annotation.Validated;

@Validated
public record PlaysiteRemoveRequest(
         @PositiveOrZero Long id
) {}
