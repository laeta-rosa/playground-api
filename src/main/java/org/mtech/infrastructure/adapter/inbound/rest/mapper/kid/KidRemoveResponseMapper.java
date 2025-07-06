package org.mtech.infrastructure.adapter.inbound.rest.mapper.kid;

import lombok.experimental.UtilityClass;
import org.mtech.application.kid.remove.KidRemoveCommandResult.KidNotFound;
import org.mtech.application.kid.remove.KidRemoveCommandResult.KidRemoved;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.kid.KidRemoveResponse;

import static org.mtech.infrastructure.adapter.inbound.rest.api.vo.RemoveStatus.KID_NOT_FOUND;
import static org.mtech.infrastructure.adapter.inbound.rest.api.vo.RemoveStatus.REMOVED;

@UtilityClass
public class KidRemoveResponseMapper {

    public static KidRemoveResponse toResponse(KidRemoved result) {
        return KidRemoveResponse.builder()
                .ticketNumber(result.ticketNumber())
                .status(REMOVED)
                .build();
    }

    public static KidRemoveResponse toResponse(KidNotFound result) {
        return KidRemoveResponse.builder()
                .ticketNumber(result.ticketNumber())
                .status(KID_NOT_FOUND)
                .build();
    }

}
