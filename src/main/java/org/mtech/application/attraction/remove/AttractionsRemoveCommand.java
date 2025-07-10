package org.mtech.application.attraction.remove;

import lombok.Builder;
import lombok.NonNull;
import org.mtech.domain.vo.PlaysiteId;

@Builder
public record AttractionsRemoveCommand(@NonNull PlaysiteId id) {}
