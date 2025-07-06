package org.mtech.infrastructure.adapter.inbound.rest.mapper.kid;

import lombok.experimental.UtilityClass;
import org.mtech.application.kid.remove.KidRemoveCommand;
import org.mtech.infrastructure.adapter.inbound.rest.api.request.kid.KidAddRequest;
import org.mtech.infrastructure.adapter.inbound.rest.api.request.kid.KidAddRequest.RequestKid;
import org.mtech.application.kid.add.KidAddCommand;
import org.mtech.domain.Kid;
import org.mtech.infrastructure.adapter.inbound.rest.api.request.kid.KidRemoveRequest;

import java.util.Random;

@UtilityClass
public class KidCommandMapper {

    public static KidAddCommand toCommand(long playsiteId, KidAddRequest request) {
        return KidAddCommand.builder()
                .playsiteId(playsiteId)
                .kid(toKid(request.kid()))
                .acceptQueue(new Random().nextBoolean())
                .build();
    }

    public static KidRemoveCommand toCommand(KidRemoveRequest request) {
        return KidRemoveCommand.builder()
                .ticketNumber(request.ticketNumber())
                .build();
    }

    static Kid toKid(RequestKid kid) {
        return new Kid()
                .setName(kid.name())
                .setAge(kid.age());
    }

}
