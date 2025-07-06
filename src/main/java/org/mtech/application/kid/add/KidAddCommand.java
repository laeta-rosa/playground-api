package org.mtech.application.kid.add;

import lombok.Builder;
import lombok.NonNull;
import org.mtech.domain.Kid;

@Builder
public record KidAddCommand(
        @NonNull Long playsiteId,
        @NonNull Kid kid,
        boolean acceptQueue
) {}
