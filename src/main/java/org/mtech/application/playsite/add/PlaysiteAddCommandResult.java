package org.mtech.application.playsite.add;

import org.mtech.domain.Attraction;

import java.util.List;

public sealed interface PlaysiteAddCommandResult {

    record PlaysiteAdded(Long id, List<Attraction> attractions) implements PlaysiteAddCommandResult {}

    record PlaysiteAttractionCapacityExceeded() implements PlaysiteAddCommandResult {}

}
