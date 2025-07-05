package org.mtech.adapter.inbound.rest.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.mtech.adapter.inbound.rest.validation.ValidAttraction;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record PlaysiteAddRequest(
         List<RequestAttraction> attractions
) {

    public record RequestAttraction(
            @NotNull @ValidAttraction String name,
            @Positive Integer count
    ) {}

}
