package org.mtech.application.playsite.query;

import org.mtech.domain.Playsite;

import java.util.List;

public sealed interface PlaysitesQueryResult {

    record PlaysitesFound(List<Playsite> playsites) implements PlaysitesQueryResult {}

    record PlaysitesNotFound() implements PlaysitesQueryResult {}

}
