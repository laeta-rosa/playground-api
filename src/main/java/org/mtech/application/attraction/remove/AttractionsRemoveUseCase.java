package org.mtech.application.attraction.remove;

import lombok.RequiredArgsConstructor;
import org.mtech.application.attraction.remove.AttractionsRemoveCommandResult.AttractionsRemoved;
import org.mtech.application.attraction.remove.AttractionsRemoveCommandResult.PlaysiteToRemoveAttractionsNotFound;
import org.mtech.application.usecase.CommandUseCase;
import org.mtech.domain.Playsite;
import org.mtech.infrastructure.adapter.outbound.database.repository.PlaysiteRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AttractionsRemoveUseCase implements CommandUseCase<AttractionsRemoveCommand, AttractionsRemoveCommandResult> {

    private final PlaysiteRepository repository;

    @Override
    @Transactional
    public AttractionsRemoveCommandResult invoke(AttractionsRemoveCommand command) {
        var playsite = repository.findById(command.playsiteId());
        return playsite
                .map(this::removeAttractions)
                .<AttractionsRemoveCommandResult>map(AttractionsRemoved::new)
                .orElseGet(PlaysiteToRemoveAttractionsNotFound::new);
    }

    private Long removeAttractions(Playsite playsite) {
        playsite.getAttractions().clear();
        playsite.getKids().clear();
        playsite.setCapacity(0);
        return repository.save(playsite).getId();
    }

}
