package org.mtech.infrastructure.adapter.inbound.rest.mapper.attraction;

import lombok.experimental.UtilityClass;
import org.mtech.application.attraction.remove.AttractionsRemoveCommand;
import org.mtech.infrastructure.adapter.inbound.rest.api.request.attraction.AttractionAddRequest;
import org.mtech.infrastructure.adapter.inbound.rest.api.request.attraction.AttractionRemoveRequest;
import org.mtech.application.attraction.add.AttractionAddCommand;
import org.mtech.application.attraction.remove.AttractionRemoveCommand;

import static org.mtech.infrastructure.adapter.inbound.rest.mapper.playsite.PlaysiteCommandMapper.toAttractions;

@UtilityClass
public class AttractionCommandMapper {

    public static AttractionAddCommand toCommand(AttractionAddRequest request, long playsiteId) {
        return AttractionAddCommand.builder()
                .playsiteId(playsiteId)
                .attractions(toAttractions(request.attraction()))
                .build();
    }

    public static AttractionRemoveCommand toCommand(AttractionRemoveRequest request) {
        return AttractionRemoveCommand.builder()
                .id(request.id())
                .build();
    }

    public static AttractionsRemoveCommand toCommand(Long playsiteId) {
        return AttractionsRemoveCommand.builder()
                .playsiteId(playsiteId)
                .build();
    }

}
