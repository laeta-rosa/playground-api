package org.mtech.application.playsite.add;

import org.mtech.domain.Attraction;
import org.mtech.domain.vo.PlaysiteId;

import java.util.List;

public sealed interface PlaysiteAddCommandResult {

    record PlaysiteAdded(PlaysiteId id, List<Attraction> attractions) implements PlaysiteAddCommandResult {}

    record PlaysiteAttractionCapacityExceeded() implements PlaysiteAddCommandResult {}

}
