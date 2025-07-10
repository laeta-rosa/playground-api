package org.mtech.application.attraction.remove;

import lombok.RequiredArgsConstructor;
import org.mtech.domain.Attraction;
import org.mtech.domain.Playsite;
import org.mtech.infrastructure.adapter.outbound.database.repository.AttractionRepository;
import org.mtech.application.attraction.remove.AttractionRemoveCommandResult.AttractionNotFound;
import org.mtech.application.attraction.remove.AttractionRemoveCommandResult.AttractionRemoved;
import org.mtech.application.usecase.CommandUseCase;
import org.mtech.infrastructure.adapter.outbound.database.repository.PlaysiteRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AttractionRemoveUseCase implements CommandUseCase<AttractionRemoveCommand, AttractionRemoveCommandResult> {

    private final AttractionRepository attractionRepository;
    private final PlaysiteRepository playsiteRepository;

    @Override
    @Transactional
    public AttractionRemoveCommandResult invoke(AttractionRemoveCommand command) {
        return attractionRepository.findById(command.id().value())
                .map(attraction -> {
                    remove(attraction);
                    return (AttractionRemoveCommandResult) new AttractionRemoved(command.id());
                })
                .orElseGet(() -> new AttractionNotFound(command.id()));
    }

    private void remove(Attraction attraction) {
        var playsite = attraction.getPlaysite();
        playsite.removeAttraction(attraction);
        adjustKidsToCapacity(playsite);
        playsiteRepository.save(playsite);
    }

    private void adjustKidsToCapacity(Playsite playsite) {
        if (playsite.getKids().size() > playsite.getCapacity()) {
            var kidsOverCapacity = playsite.getKids().stream()
                    .skip(playsite.getCapacity())
                    .toList();
            kidsOverCapacity.forEach(playsite::removeKid);
        }
    }

}
