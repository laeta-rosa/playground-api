package org.mtech.infrastructure.adapter.inbound.rest.api.response.kid;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.NonNull;
import org.mtech.domain.vo.KidStatus;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record KidAddResponse(
        @NonNull Status status,
        ResponseKid kid
) {
    @Builder
    public record ResponseKid(
            @NonNull String ticketNumber,
            @NonNull LocalDateTime ticketDateTime,
            @NonNull String name,
            @NonNull KidStatus status,
            Integer age
    ) {}

    public enum Status {
        PLAYING,
        QUEUED,
        REFUSED_QUEUE,
        QUEUE_FULL,
        PLAYSITE_OR_ATTRACTION_NOT_FOUND
    }
}
