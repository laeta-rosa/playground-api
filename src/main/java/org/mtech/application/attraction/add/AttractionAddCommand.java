package org.mtech.application.attraction.add;

import lombok.Builder;
import lombok.NonNull;
import org.mtech.domain.Attraction;
import org.mtech.domain.vo.PlaysiteId;

import java.util.List;

@Builder
public record AttractionAddCommand(
        @NonNull PlaysiteId id,
        @NonNull List<Attraction> attractions
) {}
