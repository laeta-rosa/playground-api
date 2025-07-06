package org.mtech.application.playsite.add;

import lombok.RequiredArgsConstructor;
import org.mtech.infrastructure.adapter.outbound.database.repository.PlaysiteRepository;
import org.mtech.application.playsite.add.PlaysiteAddCommandResult.PlaysiteAdded;
import org.mtech.application.playsite.add.PlaysiteAddCommandResult.PlaysiteAttractionCapacityExceeded;
import org.mtech.application.usecase.CommandUseCase;
import org.mtech.infrastructure.config.PlaysiteConfig;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PlaysiteAddUseCase implements CommandUseCase<PlaysiteAddCommand, PlaysiteAddCommandResult> {

    private final PlaysiteConfig config;
    private final PlaysiteRepository repository;

    @Override
    @Transactional
    public PlaysiteAddCommandResult invoke(PlaysiteAddCommand command) {
        if (command.playsite().getAttractions().size() >= config.attractions().maxCount()) {
            return new PlaysiteAttractionCapacityExceeded();
        }

        var playsite = repository.save(command.playsite());
        return new PlaysiteAdded(playsite.getId(), playsite.getAttractions());
    }

}
