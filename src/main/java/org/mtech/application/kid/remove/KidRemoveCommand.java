package org.mtech.application.kid.remove;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record KidRemoveCommand(@NonNull String ticketNumber) {}
