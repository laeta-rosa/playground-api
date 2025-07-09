package org.mtech.infrastructure.adapter.inbound.rest.api.response.kid;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record KidsTotalCountResponse(
        @NonNull Long count
) {}
