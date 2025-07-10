package org.mtech.application.kid.add;

import org.mtech.domain.Kid;

public sealed interface KidAddCommandResult {

    record KidPlaying(Kid kid) implements KidAddCommandResult {}

    record KidWaiting(Kid kid) implements KidAddCommandResult {}

    record KidRefusedToWait() implements KidAddCommandResult {}

    record QueueFull() implements KidAddCommandResult {}

    record PlaysiteHasNoAttractionsToAddKid() implements KidAddCommandResult {}

    record PlaysiteToAddKidNotFound() implements KidAddCommandResult {}

}
