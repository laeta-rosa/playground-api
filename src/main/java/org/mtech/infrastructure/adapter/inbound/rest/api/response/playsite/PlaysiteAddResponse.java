package org.mtech.infrastructure.adapter.inbound.rest.api.response.playsite;

import lombok.Builder;
import lombok.NonNull;

import org.mtech.infrastructure.adapter.inbound.rest.api.response.playsite.PlaysiteResponse.ResponseAttraction;

import java.util.List;

@Builder
public record PlaysiteAddResponse(
        @NonNull Long id,
        @NonNull Status status,
        List<ResponseAttraction> attractions
) {

    public enum Status {
        ADDED,
        ATTRACTION_CAPACITY_EXCEEDED
    }

}
