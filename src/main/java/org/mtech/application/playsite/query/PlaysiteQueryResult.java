package org.mtech.application.playsite.query;

import org.mtech.domain.Playsite;

public sealed interface PlaysiteQueryResult {

    record PlaysiteResult(Playsite playsite) implements PlaysiteQueryResult {}

    record PlaysiteNotFound(Long id) implements PlaysiteQueryResult {}

}
