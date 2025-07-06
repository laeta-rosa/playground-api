package org.mtech.infrastructure.adapter.inbound.rest.api.response.kid;

import lombok.Builder;
import lombok.NonNull;
import org.mtech.infrastructure.adapter.inbound.rest.api.vo.RemoveStatus;

@Builder
public record KidRemoveResponse(
        @NonNull String ticketNumber,
        @NonNull RemoveStatus status
) {}
