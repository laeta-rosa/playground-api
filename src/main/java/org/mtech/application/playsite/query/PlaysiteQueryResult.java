package org.mtech.application.playsite.query;

import org.mtech.domain.Playsite;

public sealed interface PlaysiteQueryResult {

    record PlaysiteFound(Playsite playsite) implements PlaysiteQueryResult {}

    record PlaysiteNotFound(Long id) implements PlaysiteQueryResult {}

}
