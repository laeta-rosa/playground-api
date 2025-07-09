package org.mtech.application.kid.count;

public sealed interface KidsCountQueryResult {

    record KidsCountCalculated(long count) implements KidsCountQueryResult {}

}
