package org.mtech.application.kid.add;

import lombok.RequiredArgsConstructor;
import org.mtech.domain.Kid;
import org.mtech.domain.Playsite;
import org.mtech.application.kid.add.KidAddCommandResult.KidPlaying;
import org.mtech.application.kid.add.KidAddCommandResult.KidRefusedToWait;
import org.mtech.application.kid.add.KidAddCommandResult.KidWaiting;
import org.mtech.application.kid.add.KidAddCommandResult.PlaysiteToAddKidNotFound;
import org.mtech.application.kid.add.KidAddCommandResult.QueueFull;
import org.mtech.application.usecase.CommandUseCase;
import org.mtech.domain.vo.KidStatus;
import org.mtech.infrastructure.adapter.outbound.database.repository.PlaysiteRepository;
import org.mtech.infrastructure.config.PlaysiteConfig;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.mtech.domain.vo.KidStatus.PLAYING;
import static org.mtech.domain.vo.KidStatus.WAITING;

@Component
@RequiredArgsConstructor
public class KidAddUseCase implements CommandUseCase<KidAddCommand, KidAddCommandResult> {

    private final PlaysiteConfig config;
    private final PlaysiteRepository repository;

    @Override
    @Transactional
    public KidAddCommandResult invoke(KidAddCommand command) {
        System.out.println("Trying to fetch" + command.id().value());
        return repository.findById(command.id().value())
                .map(playsite -> tryAddKid(playsite, command))
                .orElseGet(PlaysiteToAddKidNotFound::new);
    }

    private KidAddCommandResult tryAddKid(Playsite playsite, KidAddCommand command) {
        if (playsite.getKids().size() < playsite.getCapacity()) {
            return addKid(playsite, command.kid(), PLAYING);
        } else if (command.acceptQueue()) {
            return addKid(playsite, command.kid(), WAITING);
        }
        return new KidRefusedToWait();
    }

    private KidAddCommandResult addKid(Playsite playsite, Kid kid, KidStatus status) {
        if (playsite.getKidsQueue().size() >= config.queue().size()) {
            return new QueueFull();
        }
        playsite.addKid(kid, status);
        repository.save(playsite);
        return switch (status) {
            case PLAYING -> new KidPlaying(kid);
            case WAITING -> new KidWaiting(kid);
        };
    }

}
