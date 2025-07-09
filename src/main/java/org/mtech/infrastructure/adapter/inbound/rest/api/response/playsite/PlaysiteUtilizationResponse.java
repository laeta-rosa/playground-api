package org.mtech.infrastructure.adapter.inbound.rest.api.response.playsite;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record PlaysiteUtilizationResponse(
        @NonNull String utilization
) {}
