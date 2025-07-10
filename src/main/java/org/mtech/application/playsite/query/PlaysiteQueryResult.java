package org.mtech.application.playsite.query;

import org.mtech.domain.Playsite;
import org.mtech.domain.vo.PlaysiteId;

public sealed interface PlaysiteQueryResult {

    record PlaysiteFound(Playsite playsite) implements PlaysiteQueryResult {}

    record PlaysiteNotFound(PlaysiteId id) implements PlaysiteQueryResult {}

}
