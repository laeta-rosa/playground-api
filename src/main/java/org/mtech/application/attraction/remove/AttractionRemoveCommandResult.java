package org.mtech.application.attraction.remove;

import org.mtech.domain.vo.AttractionId;

public sealed interface AttractionRemoveCommandResult {

    record AttractionRemoved(AttractionId id) implements AttractionRemoveCommandResult {}

    record AttractionNotFound(AttractionId id) implements AttractionRemoveCommandResult {}

}
