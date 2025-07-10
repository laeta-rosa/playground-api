package org.mtech.application.attraction.remove;

import org.mtech.domain.vo.PlaysiteId;

public sealed interface AttractionsRemoveCommandResult {

    record AttractionsRemoved(PlaysiteId id) implements AttractionsRemoveCommandResult {}

    record PlaysiteToRemoveAttractionsNotFound(PlaysiteId id) implements AttractionsRemoveCommandResult {}

}
