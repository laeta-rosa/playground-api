package org.mtech.infrastructure.adapter.inbound.rest.mapper.kid;

import lombok.experimental.UtilityClass;
import org.mtech.domain.Kid;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.kid.KidAddResponse;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.kid.KidAddResponse.ResponseKid;
import org.mtech.application.kid.add.KidAddCommandResult.KidPlaying;
import org.mtech.application.kid.add.KidAddCommandResult.KidWaiting;

import java.util.List;

import static org.mtech.infrastructure.adapter.inbound.rest.api.response.kid.KidAddResponse.Status.*;

@UtilityClass
public class KidAddResponseMapper {

    public static KidAddResponse toResponse(KidPlaying result) {
        return KidAddResponse.builder()
                .kid(toResponseKid(result.kid()))
                .status(PLAYING)
                .build();
    }

    public static KidAddResponse toResponse(KidWaiting result) {
        return KidAddResponse.builder()
                .kid(toResponseKid(result.kid()))
                .status(QUEUED)
                .build();
    }

    public static KidAddResponse toEmptyResponse() {
        return KidAddResponse.builder()
                .status(PLAYSITE_NOT_FOUND)
                .build();
    }

    public static KidAddResponse toRefusedResponse() {
        return KidAddResponse.builder()
                .status(REFUSED_QUEUE)
                .build();
    }

    public static KidAddResponse toFullQueueResponse() {
        return KidAddResponse.builder()
                .status(QUEUE_FULL)
                .build();
    }

    public static List<ResponseKid> toResponseKids(List<Kid> kids) {
        return kids.stream()
                .map(KidAddResponseMapper::toResponseKid)
                .toList();
    }

    static ResponseKid toResponseKid(Kid kid) {
        return ResponseKid.builder()
                .ticketNumber(kid.getTicketNumber())
                .name(kid.getName())
                .age(kid.getAge())
                .ticketDateTime(kid.getTicketDateTime())
                .build();
    }

}
