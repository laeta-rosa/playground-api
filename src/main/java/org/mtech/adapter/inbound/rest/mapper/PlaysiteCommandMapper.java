package org.mtech.adapter.inbound.rest.mapper;

import lombok.experimental.UtilityClass;
import org.mtech.adapter.inbound.rest.request.PlaysiteAddRequest;
import org.mtech.core.playsite.add.PlaysiteAddCommand;
import org.mtech.domain.Attraction;

import java.util.List;

@UtilityClass
public class PlaysiteCommandMapper {

    public static PlaysiteAddCommand toCommand(PlaysiteAddRequest request) {
        return PlaysiteAddCommand.builder()
            .attractions(toAttractions(request.attractions()))
            .build();
    }

    static List<Attraction> toAttractions(List<PlaysiteAddRequest.RequestAttraction> playsites) {
        return playsites.stream().map(AttractionMapper::toAttraction).toList();
    }

}
