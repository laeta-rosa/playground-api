package org.mtech.infrastructure.adapter.inbound.rest.api.response.playsite;

import lombok.Builder;
import lombok.NonNull;
import org.mtech.infrastructure.adapter.inbound.rest.api.vo.RemoveStatus;

@Builder
public record PlaysiteRemoveResponse(
        @NonNull Long id,
        @NonNull RemoveStatus status
) {}
