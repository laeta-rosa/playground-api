package org.mtech.application.playsite.remove;

import lombok.RequiredArgsConstructor;
import org.mtech.infrastructure.adapter.outbound.database.repository.PlaysiteRepository;
import org.mtech.application.playsite.remove.PlaysiteRemoveCommandResult.PlaysiteNotFound;
import org.mtech.application.playsite.remove.PlaysiteRemoveCommandResult.PlaysiteRemoved;
import org.mtech.application.usecase.CommandUseCase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PlaysiteRemoveUseCase implements CommandUseCase<PlaysiteRemoveCommand, PlaysiteRemoveCommandResult> {

    private final PlaysiteRepository repository;

    @Override
    @Transactional
    public PlaysiteRemoveCommandResult invoke(PlaysiteRemoveCommand command) {
        if (repository.existsById(command.id())) {
            repository.deleteById(command.id());
            return new PlaysiteRemoved(command.id());
        } else {
            return new PlaysiteNotFound(command.id());
        }
    }

}
