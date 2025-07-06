package org.mtech.application.attraction.add;

import org.mtech.domain.Attraction;

import java.util.List;

public sealed interface AttractionAddCommandResult {

    record AttractionAdded(List<Attraction> updatedList) implements AttractionAddCommandResult {}

    record PlaysiteAttractionCapacityExceeded() implements AttractionAddCommandResult {}

    record PlaysiteToAddAttractionNotFound() implements AttractionAddCommandResult {}

}
