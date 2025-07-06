package org.mtech.application.attraction.remove;

public sealed interface AttractionsRemoveCommandResult {

    record AttractionsRemoved(Long id) implements AttractionsRemoveCommandResult {}

    record PlaysiteToRemoveAttractionsNotFound() implements AttractionsRemoveCommandResult {}

}
