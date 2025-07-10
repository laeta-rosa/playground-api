package org.mtech.infrastructure.adapter.inbound.rest.mapper.attraction;

import lombok.experimental.UtilityClass;

import org.mtech.infrastructure.adapter.inbound.rest.api.response.attraction.AttractionRemoveResponse;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.attraction.AttractionsRemoveResponse;
import org.mtech.application.attraction.remove.AttractionRemoveCommandResult.AttractionNotFound;
import org.mtech.application.attraction.remove.AttractionRemoveCommandResult.AttractionRemoved;

import static org.mtech.infrastructure.adapter.inbound.rest.api.vo.RemoveStatus.*;

@UtilityClass
public class AttractionRemoveResponseMapper {

    public static AttractionsRemoveResponse toResponse() {
        return AttractionsRemoveResponse.builder()
                .status(PLAYSITE_NOT_FOUND)
                .build();
    }

    public static AttractionsRemoveResponse toResponse(Long id) {
        return AttractionsRemoveResponse.builder()
                .status(REMOVED)
                .id(id)
                .build();
    }

    public static AttractionRemoveResponse toResponse(AttractionRemoved attraction) {
        return AttractionRemoveResponse.builder()
                .id(attraction.id().value())
                .status(REMOVED)
                .build();
    }

    public static AttractionRemoveResponse toResponse(AttractionNotFound attraction) {
        return AttractionRemoveResponse.builder()
                .id(attraction.id().value())
                .status(ATTRACTION_NOT_FOUND)
                .build();
    }

}
