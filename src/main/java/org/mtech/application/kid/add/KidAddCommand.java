package org.mtech.application.kid.add;

import lombok.Builder;
import lombok.NonNull;
import org.mtech.domain.Kid;
import org.mtech.domain.vo.PlaysiteId;

@Builder
public record KidAddCommand(
        @NonNull PlaysiteId id,
        @NonNull Kid kid,
        boolean acceptQueue
) {}
