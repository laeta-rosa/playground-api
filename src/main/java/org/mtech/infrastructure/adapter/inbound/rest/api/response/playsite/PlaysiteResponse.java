package org.mtech.infrastructure.adapter.inbound.rest.api.response.playsite;

import lombok.Builder;
import lombok.NonNull;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.kid.KidAddResponse.ResponseKid;

import java.util.List;

@Builder
public record PlaysiteResponse(
        @NonNull Long id,
        @NonNull List<ResponseAttraction> attractions,
        @NonNull List<ResponseKid> kids
) {

    @Builder
    public record ResponseAttraction(
            @NonNull Long id,
            @NonNull String name
    ) {}

}
