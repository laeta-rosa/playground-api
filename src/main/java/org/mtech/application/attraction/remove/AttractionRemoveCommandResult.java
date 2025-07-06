package org.mtech.application.attraction.remove;

public sealed interface AttractionRemoveCommandResult {

    record AttractionRemoved(Long id) implements AttractionRemoveCommandResult {}

    record AttractionNotFound(Long id) implements AttractionRemoveCommandResult {}

}
