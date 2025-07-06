package org.mtech.application.playsite.remove;

public sealed interface PlaysiteRemoveCommandResult {

    record PlaysiteRemoved(Long id) implements PlaysiteRemoveCommandResult {}

    record PlaysiteNotFound(Long id) implements PlaysiteRemoveCommandResult {}

}
