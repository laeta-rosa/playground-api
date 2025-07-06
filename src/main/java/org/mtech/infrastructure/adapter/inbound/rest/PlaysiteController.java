package org.mtech.infrastructure.adapter.inbound.rest;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.mtech.application.playsite.query.PlaysiteQueryUseCase;
import org.mtech.application.playsite.query.PlaysiteQueryResult.PlaysiteResult;
import org.mtech.application.playsite.query.PlaysiteQueryResult.PlaysiteNotFound;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.playsite.PlaysiteResponse;
import org.mtech.infrastructure.adapter.inbound.rest.mapper.playsite.PlaysiteQueryMapper;
import org.springframework.web.bind.annotation.*;
import static org.mtech.infrastructure.adapter.inbound.rest.mapper.playsite.PlaysiteResponseMapper.toResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playsite")
public class PlaysiteController {

    private final PlaysiteQueryUseCase playsiteQueryUseCase;

    @OpenAPI.GetPlaysite
    @GetMapping("/{playsiteId}")
    public PlaysiteResponse getPlaysite(@PathVariable Long playsiteId) {
        var result = playsiteQueryUseCase.invoke(PlaysiteQueryMapper.toQuery(playsiteId));

        return switch (result) {
            case PlaysiteResult p -> toResponse(p.playsite());
            case PlaysiteNotFound ignored -> null;
        };
    }

    @UtilityClass
    private static class OpenAPI {

        @Operation(
                summary = "Returns a list of the playsite's attractions"
        )
        @interface GetPlaysite {}

    }

}
