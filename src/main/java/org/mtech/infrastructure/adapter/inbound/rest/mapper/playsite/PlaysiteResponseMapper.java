package org.mtech.infrastructure.adapter.inbound.rest.mapper.playsite;

import lombok.experimental.UtilityClass;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.playsite.*;
import org.mtech.application.playsite.add.PlaysiteAddCommandResult.PlaysiteAdded;
import org.mtech.application.playsite.query.PlaysitesQueryResult.PlaysitesFound;
import org.mtech.application.playsite.remove.PlaysiteRemoveCommandResult.PlaysiteNotFound;
import org.mtech.application.playsite.remove.PlaysiteRemoveCommandResult.PlaysiteRemoved;
import org.mtech.domain.Playsite;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.mtech.infrastructure.adapter.inbound.rest.api.response.playsite.PlaysiteAddResponse.Status.ADDED;
import static org.mtech.infrastructure.adapter.inbound.rest.api.response.playsite.PlaysiteAddResponse.Status.ATTRACTION_CAPACITY_EXCEEDED;
import static org.mtech.infrastructure.adapter.inbound.rest.api.vo.RemoveStatus.PLAYSITE_NOT_FOUND;
import static org.mtech.infrastructure.adapter.inbound.rest.api.vo.RemoveStatus.REMOVED;
import static org.mtech.infrastructure.adapter.inbound.rest.mapper.attraction.AttractionAddResponseMapper.toAttractions;
import static org.mtech.infrastructure.adapter.inbound.rest.mapper.kid.KidAddResponseMapper.toResponseKids;

@UtilityClass
public class PlaysiteResponseMapper {

    public static PlaysitesResponse toEmptyResponse() {
        return new PlaysitesResponse(emptyList());
    }

    public static PlaysitesResponse toResponse(PlaysitesFound queryResult) {
        return PlaysitesResponse.builder()
            .playsites(toPlaysites(queryResult.playsites()))
            .build();
    }

    public static PlaysiteAddResponse toResponse(PlaysiteAdded playsite) {
        return PlaysiteAddResponse.builder()
                .id(playsite.id().value())
                .status(ADDED)
                .attractions(toAttractions(playsite.attractions()))
                .build();
    }

    public static PlaysiteAddResponse toRejectedResponse() {
        return PlaysiteAddResponse.builder()
                .status(ATTRACTION_CAPACITY_EXCEEDED)
                .build();
    }

    public static PlaysiteRemoveResponse toResponse(PlaysiteRemoved playsite) {
        return PlaysiteRemoveResponse.builder()
                .id(playsite.id().value())
                .status(REMOVED)
                .build();
    }

    public static PlaysiteRemoveResponse toResponse(PlaysiteNotFound playsite) {
        return PlaysiteRemoveResponse.builder()
                .id(playsite.id().value())
                .status(PLAYSITE_NOT_FOUND)
                .build();
    }

    public static PlaysiteResponse toResponse(Playsite playsite) {
        return PlaysiteResponse.builder()
                .id(playsite.getId())
                .attractions(toAttractions(playsite.getAttractions()))
                .kids(toResponseKids(playsite.getKids()))
                .build();
    }

    public static PlaysiteUtilizationResponse toResponse(String utilizationPercentage) {
        return PlaysiteUtilizationResponse.builder()
                .utilization(utilizationPercentage)
                .build();
    }

    static List<PlaysiteResponse> toPlaysites(List<Playsite> playsites) {
        return playsites.stream().map(PlaysiteResponseMapper::toResponse).toList();
    }

}
