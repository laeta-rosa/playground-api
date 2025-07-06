package org.mtech.infrastructure.adapter.inbound.rest.api.response.attraction;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.NonNull;

import org.mtech.infrastructure.adapter.inbound.rest.api.response.playsite.PlaysiteResponse.ResponseAttraction;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record AttractionAddResponse(
        @NonNull Status status,
        List<ResponseAttraction> updatedAttractions
) {

    public enum Status {
        ADDED,
        PLAYSITE_NOT_FOUND,
        ATTRACTION_CAPACITY_EXCEEDED
    }

}
