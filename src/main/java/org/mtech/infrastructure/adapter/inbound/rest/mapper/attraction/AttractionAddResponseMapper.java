package org.mtech.infrastructure.adapter.inbound.rest.mapper.attraction;

import lombok.experimental.UtilityClass;
import org.mtech.domain.Attraction;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.attraction.AttractionAddResponse;

import java.util.List;

import org.mtech.infrastructure.adapter.inbound.rest.api.request.attraction.AttractionAddRequest.RequestAttraction;

import org.mtech.infrastructure.adapter.inbound.rest.api.response.playsite.PlaysiteResponse.ResponseAttraction;
import org.mtech.application.attraction.add.AttractionAddCommandResult.AttractionAdded;

import static org.mtech.infrastructure.adapter.inbound.rest.api.response.attraction.AttractionAddResponse.Status.*;

@UtilityClass
public class AttractionAddResponseMapper {

    public static AttractionAddResponse toResponse(AttractionAdded attraction) {
        return AttractionAddResponse.builder()
                .status(ADDED)
                .updatedAttractions(toAttractions(attraction.updatedList()))
                .build();
    }

    public static AttractionAddResponse toEmptyResponse() {
        return AttractionAddResponse.builder()
                .status(PLAYSITE_NOT_FOUND)
                .build();
    }

    public static AttractionAddResponse toRejectedAddResponse() {
        return AttractionAddResponse.builder()
                .status(ATTRACTION_CAPACITY_EXCEEDED)
                .build();
    }

    public static List<ResponseAttraction> toAttractions(List<Attraction> attractions) {
        return attractions.stream().map(AttractionAddResponseMapper::toAttraction).toList();
    }

    public static Attraction toAttraction(RequestAttraction attraction) {
        return new Attraction().setName(attraction.name());
    }

    static ResponseAttraction toAttraction(Attraction attraction) {
        return ResponseAttraction.builder()
                .id(attraction.getId())
                .name(attraction.getName())
                .build();
    }

}
