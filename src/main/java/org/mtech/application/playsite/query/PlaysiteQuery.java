package org.mtech.application.playsite.query;

import lombok.Builder;
import lombok.NonNull;
import org.mtech.domain.vo.PlaysiteId;

@Builder
public record PlaysiteQuery(@NonNull PlaysiteId id) {}
