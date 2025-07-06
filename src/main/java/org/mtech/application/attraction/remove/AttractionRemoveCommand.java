package org.mtech.application.attraction.remove;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record AttractionRemoveCommand(@NonNull Long id) {}
