package org.mtech.infrastructure.adapter.inbound.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mtech.application.kid.add.KidAddCommandResult.KidPlaying;
import org.mtech.application.kid.add.KidAddCommandResult.KidRefusedToWait;
import org.mtech.application.kid.add.KidAddCommandResult.KidWaiting;
import org.mtech.application.kid.add.KidAddCommandResult.PlaysiteHasNoAttractionsToAddKid;
import org.mtech.application.kid.add.KidAddCommandResult.PlaysiteToAddKidNotFound;
import org.mtech.application.kid.add.KidAddCommandResult.QueueFull;
import org.mtech.application.kid.add.KidAddUseCase;
import org.mtech.application.kid.remove.KidRemoveCommandResult.KidNotFound;
import org.mtech.application.kid.remove.KidRemoveCommandResult.KidRemoved;
import org.mtech.application.kid.remove.KidRemoveUseCase;
import org.mtech.infrastructure.adapter.inbound.rest.api.request.kid.KidAddRequest;
import org.mtech.infrastructure.adapter.inbound.rest.api.request.kid.KidRemoveRequest;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.kid.KidAddResponse;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.kid.KidRemoveResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

import static org.mtech.infrastructure.adapter.inbound.rest.mapper.kid.KidCommandMapper.toCommand;
import static org.mtech.infrastructure.adapter.inbound.rest.mapper.kid.KidAddResponseMapper.*;
import static org.mtech.infrastructure.adapter.inbound.rest.mapper.kid.KidRemoveResponseMapper.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playsite")
public class KidController {

    private final Random random;
    private final KidAddUseCase kidAddUseCase;
    private final KidRemoveUseCase kidRemoveUseCase;

    @PostMapping("/{playsiteId}/kids:add")
    public KidAddResponse addKid(@PathVariable Long playsiteId, @Valid @RequestBody KidAddRequest request) {
        var result = kidAddUseCase.invoke(toCommand(playsiteId, request, random));

        return switch (result) {
            case KidPlaying k -> toResponse(k);
            case KidWaiting k -> toResponse(k);
            case KidRefusedToWait ignored -> toRefusedResponse();
            case QueueFull ignored -> toFullQueueResponse();
            case PlaysiteHasNoAttractionsToAddKid ignored -> toEmptyResponse();
            case PlaysiteToAddKidNotFound ignored -> toEmptyResponse();
        };
    }

    @DeleteMapping("/kids:remove")
    public KidRemoveResponse removeKid(@Valid @RequestBody KidRemoveRequest request) {
        var result = kidRemoveUseCase.invoke(toCommand(request));

        return switch (result) {
            case KidRemoved k -> toResponse(k);
            case KidNotFound k -> toResponse(k);
        };
    }

}
