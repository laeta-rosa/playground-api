package org.mtech.infrastructure.adapter.inbound.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mtech.application.kid.count.KidsCountQueryResult.KidsCountCalculated;
import org.mtech.application.kid.count.KidsCountQueryUseCase;
import org.mtech.infrastructure.adapter.inbound.rest.api.request.playsite.PlaysiteAddRequest;
import org.mtech.infrastructure.adapter.inbound.rest.api.request.playsite.PlaysiteRemoveRequest;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.kid.KidsTotalCountResponse;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.playsite.PlaysiteAddResponse;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.playsite.PlaysitesResponse;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.playsite.PlaysiteRemoveResponse;
import org.mtech.application.playsite.add.PlaysiteAddCommandResult.PlaysiteAdded;
import org.mtech.application.playsite.add.PlaysiteAddCommandResult.PlaysiteAttractionCapacityExceeded;
import org.mtech.application.playsite.add.PlaysiteAddUseCase;
import org.mtech.application.playsite.query.PlaysitesQueryResult.PlaysitesFound;
import org.mtech.application.playsite.query.PlaysitesQueryResult.PlaysitesNotFound;
import org.mtech.application.playsite.query.PlaysitesQueryUseCase;
import org.mtech.application.playsite.remove.PlaysiteRemoveCommandResult.PlaysiteNotFound;
import org.mtech.application.playsite.remove.PlaysiteRemoveCommandResult.PlaysiteRemoved;
import org.mtech.application.playsite.remove.PlaysiteRemoveUseCase;
import org.springframework.web.bind.annotation.*;

import static org.mtech.infrastructure.adapter.inbound.rest.mapper.kid.KidCountResponseMapper.*;
import static org.mtech.infrastructure.adapter.inbound.rest.mapper.playsite.PlaysiteCommandMapper.toCommand;
import static org.mtech.infrastructure.adapter.inbound.rest.mapper.playsite.PlaysiteCommandMapper.toDefaultCommand;
import static org.mtech.infrastructure.adapter.inbound.rest.mapper.playsite.PlaysiteResponseMapper.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class PlaygroundController {

    private final PlaysiteAddUseCase playsiteAddUseCase;
    private final PlaysiteRemoveUseCase playsiteRemoveUseCase;
    private final PlaysitesQueryUseCase playsitesQueryUseCase;
    private final KidsCountQueryUseCase kidsCountQueryUseCase;

    @GetMapping("/playsites")
    public PlaysitesResponse getPlaysites() {
        var result = playsitesQueryUseCase.invoke();

        return switch (result) {
            case PlaysitesFound p -> toResponse(p);
            case PlaysitesNotFound ignored -> toEmptyResponse();
        };
    }

    @PostMapping("/playsites:add")
    public PlaysiteAddResponse addPlaysite(@RequestBody PlaysiteAddRequest request) {
        var result = playsiteAddUseCase.invoke(toCommand(request));

        return switch (result) {
            case PlaysiteAdded p -> toResponse(p);
            case PlaysiteAttractionCapacityExceeded ignored -> toRejectedResponse();
        };
    }

    @PostMapping("/playsites:addDefault")
    public PlaysiteAddResponse addDefaultPlaysite() {
        var result = playsiteAddUseCase.invoke(toDefaultCommand());

        return switch (result) {
            case PlaysiteAdded p -> toResponse(p);
            case PlaysiteAttractionCapacityExceeded ignored -> toRejectedResponse();
        };
    }

    @DeleteMapping("/playsites:remove")
    public PlaysiteRemoveResponse removePlaysite(@Valid @RequestBody PlaysiteRemoveRequest request) {
        var result = playsiteRemoveUseCase.invoke(toCommand(request));

        return switch (result) {
            case PlaysiteRemoved p -> toResponse(p);
            case PlaysiteNotFound nf -> toResponse(nf);
        };
    }

    @GetMapping("/kids:count")
    public KidsTotalCountResponse getKidsCount() {
        var result = kidsCountQueryUseCase.invoke();

        return switch (result) {
            case KidsCountCalculated p -> toResponse(p);
        };
    }

}
