package org.mtech.adapter.inbound.rest.response;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

@Builder
public record PlaysitesResponse(
        @NonNull List<ResponsePlaysite> playsites
) {

    @Builder
    public record ResponsePlaysite(
            @NonNull Long id,
            @NonNull List<ResponseAttraction> attractions
    ) {

        @Builder
        public record ResponseAttraction(
                @NonNull Long id,
                @NonNull String name
        ) {}

    }

}
