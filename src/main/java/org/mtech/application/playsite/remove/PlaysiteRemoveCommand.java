package org.mtech.application.playsite.remove;

import lombok.Builder;
import lombok.NonNull;
import org.mtech.domain.vo.PlaysiteId;

@Builder
public record PlaysiteRemoveCommand(@NonNull PlaysiteId id) {}
