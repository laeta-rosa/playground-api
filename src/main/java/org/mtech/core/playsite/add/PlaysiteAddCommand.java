package org.mtech.core.playsite.add;

import lombok.Builder;
import org.mtech.domain.Attraction;

import java.util.List;

@Builder
public record PlaysiteAddCommand(List<Attraction> attractions) {}
