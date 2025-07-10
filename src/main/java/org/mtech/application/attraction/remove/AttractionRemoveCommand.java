package org.mtech.application.attraction.remove;

import lombok.Builder;
import lombok.NonNull;
import org.mtech.domain.vo.AttractionId;

@Builder
public record AttractionRemoveCommand(@NonNull AttractionId id) {}
