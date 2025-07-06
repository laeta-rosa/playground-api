package org.mtech.infrastructure.adapter.inbound.rest.api.response.playsite;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

@Builder
public record PlaysitesResponse(
        @NonNull List<PlaysiteResponse> playsites
) {}
