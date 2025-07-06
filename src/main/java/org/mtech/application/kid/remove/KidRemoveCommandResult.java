package org.mtech.application.kid.remove;

public sealed interface KidRemoveCommandResult {

    record KidRemoved(String ticketNumber) implements KidRemoveCommandResult {}

    record KidNotFound(String ticketNumber) implements KidRemoveCommandResult {}

}
