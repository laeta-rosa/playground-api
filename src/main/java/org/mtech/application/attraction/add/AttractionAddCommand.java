package org.mtech.application.attraction.add;

import lombok.Builder;
import lombok.NonNull;
import org.mtech.domain.Attraction;

import java.util.List;

@Builder
public record AttractionAddCommand(
        @NonNull Long playsiteId,
        @NonNull List<Attraction> attractions
) {}
