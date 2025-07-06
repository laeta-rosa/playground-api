package org.mtech.application.playsite.add;

import lombok.Builder;
import lombok.NonNull;
import org.mtech.domain.Playsite;

@Builder
public record PlaysiteAddCommand(@NonNull Playsite playsite) {}
