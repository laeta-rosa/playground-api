package org.mtech.application.playsite.remove;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record PlaysiteRemoveCommand(@NonNull Long id) {}
