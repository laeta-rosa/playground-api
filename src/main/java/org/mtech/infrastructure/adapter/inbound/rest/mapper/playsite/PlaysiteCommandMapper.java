package org.mtech.infrastructure.adapter.inbound.rest.mapper.playsite;

import lombok.experimental.UtilityClass;
import org.mtech.infrastructure.adapter.inbound.rest.api.request.attraction.AttractionAddRequest.RequestAttraction;
import org.mtech.infrastructure.adapter.inbound.rest.api.request.playsite.PlaysiteAddRequest;
import org.mtech.infrastructure.adapter.inbound.rest.api.request.playsite.PlaysiteRemoveRequest;
import org.mtech.application.playsite.add.PlaysiteAddCommand;
import org.mtech.application.playsite.remove.PlaysiteRemoveCommand;
import org.mtech.domain.Attraction;
import org.mtech.domain.Playsite;

import java.util.Collection;
import java.util.List;

import static java.util.stream.IntStream.range;
import static org.mtech.domain.vo.AttractionType.*;
import static org.mtech.infrastructure.adapter.inbound.rest.mapper.attraction.AttractionAddResponseMapper.toAttraction;

@UtilityClass
public class PlaysiteCommandMapper {

    public static PlaysiteAddCommand toCommand(PlaysiteAddRequest request) {
        return PlaysiteAddCommand.builder()
            .playsite(toPlaysite(request.attractions()))
            .build();
    }

    public static PlaysiteAddCommand toDefaultCommand() {
        return PlaysiteAddCommand.builder()
                .playsite(toDefaultPlaysite())
                .build();
    }

    public static PlaysiteRemoveCommand toCommand(PlaysiteRemoveRequest request) {
        return PlaysiteRemoveCommand.builder()
                .id(request.id())
                .build();
    }

    public static List<Attraction> toAttractions(RequestAttraction attraction) {
        var attractionCount = attraction.count() == null ? 1 : attraction.count();
        return range(0, attractionCount)
                .mapToObj(i -> toAttraction(attraction))
                .toList();
    }

    static Playsite toPlaysite(List<RequestAttraction> attractions) {
        var playsite = new Playsite();
        attractions.stream()
                .map(PlaysiteCommandMapper::toAttractions)
                .flatMap(Collection::stream)
                .forEach(playsite::addAttraction);
        return playsite;
    }

    static Playsite toDefaultPlaysite() {
        var playsite = new Playsite();
        playsite.addAttraction(new Attraction().setName(SWINGS.getName()));
        playsite.addAttraction(new Attraction().setName(SLIDE.getName()));
        playsite.addAttraction(new Attraction().setName(CAROUSEL.getName()));
        playsite.addAttraction(new Attraction().setName(BALL_PIT.getName()));
        return playsite;
    }

}
