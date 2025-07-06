package org.mtech.infrastructure.adapter.inbound.rest.api.response.attraction;

import lombok.Builder;
import lombok.NonNull;
import org.mtech.infrastructure.adapter.inbound.rest.api.vo.RemoveStatus;

@Builder
public record AttractionsRemoveResponse(
        @NonNull Long id,
        @NonNull RemoveStatus status
) {}

