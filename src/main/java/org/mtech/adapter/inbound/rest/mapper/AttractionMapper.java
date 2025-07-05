package org.mtech.adapter.inbound.rest.mapper;

import lombok.experimental.UtilityClass;
import org.mtech.adapter.inbound.rest.request.PlaysiteAddRequest.RequestAttraction;
import org.mtech.adapter.inbound.rest.response.PlaysitesResponse.ResponsePlaysite.ResponseAttraction;
import org.mtech.domain.Attraction;

import java.util.List;

@UtilityClass
public class AttractionMapper {

    static List<ResponseAttraction> toAttractions(List<Attraction> attractions) {
        return attractions.stream().map(AttractionMapper::toAttraction).toList();
    }

    static ResponseAttraction toAttraction(Attraction attraction) {
        return ResponseAttraction.builder()
                .id(attraction.getId())
                .name(attraction.getName())
                .build();
    }

    static Attraction toAttraction(RequestAttraction attraction) {
        return new Attraction().setName(attraction.name());
    }
}
