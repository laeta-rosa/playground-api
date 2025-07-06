package org.mtech.infrastructure.adapter.inbound.rest.api.request.kid;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

@Validated
public record KidAddRequest(
         @NotNull RequestKid kid
) {

    public record RequestKid(
            @NotNull String name,
            @Positive Integer age
    ) {}

}
