package org.mtech.infrastructure.adapter.inbound.rest;

import lombok.RequiredArgsConstructor;
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

    @GetMapping("/{playsiteId}")
    public PlaysiteResponse getPlaysite(@PathVariable Long playsiteId) {
        var result = playsiteQueryUseCase.invoke(toQuery(playsiteId));

        return switch (result) {
            case PlaysiteFound p -> toResponse(p.playsite());
            case PlaysiteNotFound ignored -> null;
        };
    }

    @GetMapping("/{playsiteId}/utilization")
    public PlaysiteUtilizationResponse getPlaysiteUtilization(@PathVariable Long playsiteId) {
        var result = calculatePlaysiteUtilizationCommandUseCase.invoke(toQuery(playsiteId));

        return switch (result) {
            case UtilizationCalculated u -> toResponse(u.utilization().toString());
            case PlaysiteToCalculateUtilizationNotFound ignored -> null;
        };
    }

}
