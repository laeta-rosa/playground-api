package org.mtech.adapter.inbound.rest.mapper;

import lombok.experimental.UtilityClass;
import org.mtech.adapter.inbound.rest.response.PlaysitesResponse;
import org.mtech.adapter.inbound.rest.response.PlaysitesResponse.ResponsePlaysite;
import org.mtech.core.playsite.get.PlaysitesQueryResult.Playsites;
import org.mtech.domain.Playsite;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.mtech.adapter.inbound.rest.mapper.AttractionMapper.toAttractions;

@UtilityClass
public class PlaysiteMapper {

    public static PlaysitesResponse toEmptyResponse() {
        return new PlaysitesResponse(emptyList());
    }

    public static PlaysitesResponse toResponse(Playsites queryResult) {
        return PlaysitesResponse.builder()
            .playsites(toPlaysites(queryResult.playsites()))
            .build();
    }

    static List<ResponsePlaysite> toPlaysites(List<Playsite> playsites) {
        return playsites.stream().map(PlaysiteMapper::toPlaysite).toList();
    }

    static ResponsePlaysite toPlaysite(Playsite playsite) {
        return ResponsePlaysite.builder()
                .id(playsite.getId())
                .attractions(toAttractions(playsite.getAttractions()))
                .build();
    }
}
