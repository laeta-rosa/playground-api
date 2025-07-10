package org.mtech.application.playsite.remove;

import org.mtech.domain.vo.PlaysiteId;

public sealed interface PlaysiteRemoveCommandResult {

    record PlaysiteRemoved(PlaysiteId id) implements PlaysiteRemoveCommandResult {}

    record PlaysiteNotFound(PlaysiteId id) implements PlaysiteRemoveCommandResult {}

}
