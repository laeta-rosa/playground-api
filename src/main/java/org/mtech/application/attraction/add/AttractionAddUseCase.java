package org.mtech.application.attraction.add;

import lombok.RequiredArgsConstructor;
import org.mtech.domain.Playsite;
import org.mtech.application.attraction.add.AttractionAddCommandResult.AttractionAdded;
import org.mtech.application.attraction.add.AttractionAddCommandResult.PlaysiteAttractionCapacityExceeded;
import org.mtech.application.attraction.add.AttractionAddCommandResult.PlaysiteToAddAttractionNotFound;
import org.mtech.application.usecase.CommandUseCase;
import org.mtech.infrastructure.adapter.outbound.database.repository.PlaysiteRepository;
import org.mtech.infrastructure.config.PlaysiteConfig;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class AttractionAddUseCase implements CommandUseCase<AttractionAddCommand, AttractionAddCommandResult> {

    private final PlaysiteConfig config;
    private final PlaysiteRepository repository;

    @Override
    @Transactional
    public AttractionAddCommandResult invoke(AttractionAddCommand command) {
        var playsite = repository.findById(command.id().value());
        return playsite
                .map(p -> addAttractions(p, command))
                .orElseGet(PlaysiteToAddAttractionNotFound::new);
    }

    private AttractionAddCommandResult addAttractions(Playsite playsite, AttractionAddCommand command) {
        if (playsite.getAttractions().size() >= config.attractions().maxCount()) {
            return new PlaysiteAttractionCapacityExceeded();
        }
        command.attractions().forEach(playsite::addAttraction);
        repository.save(playsite);
        return new AttractionAdded(playsite.getAttractions());
    }

}
