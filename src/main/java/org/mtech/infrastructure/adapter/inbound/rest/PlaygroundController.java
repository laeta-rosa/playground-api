package org.mtech.infrastructure.adapter.inbound.rest;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
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

    @OpenAPI.GetPlaysites
    @GetMapping("/playsites")
    public PlaysitesResponse getPlaysites() {
        var result = playsitesQueryUseCase.invoke();

        return switch (result) {
            case PlaysitesFound p -> toResponse(p);
            case PlaysitesNotFound ignored -> toEmptyResponse();
        };
    }

    @OpenAPI.AddPlaysite
    @PostMapping("/playsites:add")
    public PlaysiteAddResponse addPlaysite(@Valid @RequestBody PlaysiteAddRequest request) {
        var result = playsiteAddUseCase.invoke(toCommand(request));

        return switch (result) {
            case PlaysiteAdded p -> toResponse(p);
            case PlaysiteAttractionCapacityExceeded ignored -> toRejectedResponse();
        };
    }

    @OpenAPI.AddDefaultPlaysite
    @PostMapping("/playsites:addDefault")
    public PlaysiteAddResponse addDefaultPlaysite() {
        var result = playsiteAddUseCase.invoke(toDefaultCommand());

        return switch (result) {
            case PlaysiteAdded p -> toResponse(p);
            case PlaysiteAttractionCapacityExceeded ignored -> toRejectedResponse();
        };
    }

    @OpenAPI.RemovePlaysite
    @DeleteMapping("/playsites:remove")
    public PlaysiteRemoveResponse removePlaysite(@Valid @RequestBody PlaysiteRemoveRequest request) {
        var result = playsiteRemoveUseCase.invoke(toCommand(request));

        return switch (result) {
            case PlaysiteRemoved p -> toResponse(p);
            case PlaysiteNotFound nf -> toResponse(nf);
        };
    }

    @OpenAPI.GetTotalKidCount
    @GetMapping("/kids/count")
    public KidsTotalCountResponse getKidsCount() {
        var result = kidsCountQueryUseCase.invoke();

        return switch (result) {
            case KidsCountCalculated p -> toResponse(p);
        };
    }

    @UtilityClass
    private static class OpenAPI {

        @Operation(
                summary = "Returns a list of the playground's playsites"
        )
        @interface GetPlaysites {}

        @Operation(
                summary = "Adds a playsite to the playground"
        )
        @interface AddPlaysite {}

        @Operation(
                summary = "Adds a default playsite to the playground"
        )
        @interface AddDefaultPlaysite {}

        @Operation(
                summary = "Removes a playsite from the playground"
        )
        @interface RemovePlaysite {}

        @Operation(
                summary = "Returns a total count of kids visiting the playground"
        )
        @interface GetTotalKidCount {}

    }

}
