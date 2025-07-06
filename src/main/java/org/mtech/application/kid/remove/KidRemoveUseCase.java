package org.mtech.application.kid.remove;

import lombok.RequiredArgsConstructor;
import org.mtech.application.kid.remove.KidRemoveCommandResult.KidNotFound;
import org.mtech.application.kid.remove.KidRemoveCommandResult.KidRemoved;
import org.mtech.application.usecase.CommandUseCase;
import org.mtech.domain.Kid;
import org.mtech.domain.Playsite;
import org.mtech.infrastructure.adapter.outbound.database.repository.KidRepository;
import org.mtech.infrastructure.adapter.outbound.database.repository.PlaysiteRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.mtech.domain.vo.KidStatus.PLAYING;

@Component
@RequiredArgsConstructor
public class KidRemoveUseCase implements CommandUseCase<KidRemoveCommand, KidRemoveCommandResult> {

    private final KidRepository kidRepository;
    private final PlaysiteRepository playsiteRepository;

    @Override
    @Transactional
    public KidRemoveCommandResult invoke(KidRemoveCommand command) {
        return kidRepository.findByTicketNumber(command.ticketNumber())
                .map(kid -> {
                    remove(kid);
                    return (KidRemoveCommandResult) new KidRemoved(command.ticketNumber());
                })
                .orElseGet(() -> new KidNotFound(command.ticketNumber()));
    }

    private void remove(Kid kid) {
        var playsite = kid.getPlaysite();
        playsite.removeKid(kid);
        moveKidFromQueue(playsite);
        playsiteRepository.save(playsite);
    }

    private void moveKidFromQueue(Playsite playsite) {
        kidRepository.getFirstInWaitingLine(playsite.getId())
                .ifPresent(kid -> kid.setStatus(PLAYING));
    }

}
