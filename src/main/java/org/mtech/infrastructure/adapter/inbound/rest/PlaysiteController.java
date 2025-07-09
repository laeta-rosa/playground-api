package org.mtech.infrastructure.adapter.inbound.rest;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.mtech.application.playsite.query.PlaysiteQueryUseCase;
import org.mtech.application.playsite.utilization.CalculatePlaysiteUtilizationCommandResult.UtilizationCalculated;
import org.mtech.application.playsite.utilization.CalculatePlaysiteUtilizationCommandResult.PlaysiteToCalculateUtilizationNotFound;
import org.mtech.application.playsite.query.PlaysiteQueryResult.PlaysiteFound;
import org.mtech.application.playsite.query.PlaysiteQueryResult.PlaysiteNotFound;
import org.mtech.application.playsite.utilization.CalculatePlaysiteUtilizationCommandUseCase;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.playsite.PlaysiteResponse;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.playsite.PlaysiteUtilizationResponse;
import org.springframework.web.bind.annotation.*;

import static org.mtech.infrastructure.adapter.inbound.rest.mapper.playsite.PlaysiteResponseMapper.toResponse;
import static org.mtech.infrastructure.adapter.inbound.rest.mapper.playsite.PlaysiteQueryMapper.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playsite")
public class PlaysiteController {

    private final PlaysiteQueryUseCase playsiteQueryUseCase;
    private final CalculatePlaysiteUtilizationCommandUseCase calculatePlaysiteUtilizationCommandUseCase;

    @OpenAPI.GetPlaysite
    @GetMapping("/{playsiteId}")
    public PlaysiteResponse getPlaysite(@PathVariable Long playsiteId) {
        var result = playsiteQueryUseCase.invoke(toQuery(playsiteId));

        return switch (result) {
            case PlaysiteFound p -> toResponse(p.playsite());
            case PlaysiteNotFound ignored -> null;
        };
    }

    @OpenAPI.GetPlaysiteUtilization
    @GetMapping("/{playsiteId}/utilization")
    public PlaysiteUtilizationResponse getPlaysiteUtilization(@PathVariable Long playsiteId) {
        var result = calculatePlaysiteUtilizationCommandUseCase.invoke(toQuery(playsiteId));

        return switch (result) {
            case UtilizationCalculated u -> toResponse(u.utilization().toString());
            case PlaysiteToCalculateUtilizationNotFound ignored -> null;
        };
    }

    @UtilityClass
    private static class OpenAPI {

        @Operation(
                summary = "Returns a list of the playsite's attractions and kids (playing and waiting in the queue)"
        )
        @interface GetPlaysite {}

        @Operation(
                summary = "Returns a playsite's utilization percentage"
        )
        @interface GetPlaysiteUtilization {}

    }

}
