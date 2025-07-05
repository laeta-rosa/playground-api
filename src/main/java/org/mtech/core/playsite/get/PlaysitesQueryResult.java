package org.mtech.core.playsite.get;

import org.mtech.domain.Playsite;

import java.util.List;

public sealed interface PlaysitesQueryResult {

    record Playsites(List<Playsite> playsites) implements PlaysitesQueryResult {}

    record PlaysitesNotFound() implements PlaysitesQueryResult {}

}
