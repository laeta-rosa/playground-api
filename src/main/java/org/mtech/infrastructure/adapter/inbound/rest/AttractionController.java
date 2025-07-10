package org.mtech.infrastructure.adapter.inbound.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mtech.application.attraction.add.AttractionAddCommandResult.AttractionAdded;
import org.mtech.application.attraction.add.AttractionAddCommandResult.PlaysiteAttractionCapacityExceeded;
import org.mtech.application.attraction.add.AttractionAddCommandResult.PlaysiteToAddAttractionNotFound;
import org.mtech.application.attraction.add.AttractionAddUseCase;
import org.mtech.application.attraction.remove.AttractionRemoveCommandResult.AttractionNotFound;
import org.mtech.application.attraction.remove.AttractionRemoveCommandResult.AttractionRemoved;
import org.mtech.application.attraction.remove.AttractionRemoveUseCase;
import org.mtech.application.attraction.remove.AttractionsRemoveCommandResult.AttractionsRemoved;
import org.mtech.application.attraction.remove.AttractionsRemoveCommandResult.PlaysiteToRemoveAttractionsNotFound;
import org.mtech.application.attraction.remove.AttractionsRemoveUseCase;
import org.mtech.infrastructure.adapter.inbound.rest.api.request.attraction.AttractionAddRequest;
import org.mtech.infrastructure.adapter.inbound.rest.api.request.attraction.AttractionRemoveRequest;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.attraction.AttractionAddResponse;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.attraction.AttractionRemoveResponse;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.attraction.AttractionsRemoveResponse;
import org.springframework.web.bind.annotation.*;

import static org.mtech.infrastructure.adapter.inbound.rest.mapper.attraction.AttractionAddResponseMapper.toResponse;
import static org.mtech.infrastructure.adapter.inbound.rest.mapper.attraction.AttractionAddResponseMapper.*;
import static org.mtech.infrastructure.adapter.inbound.rest.mapper.attraction.AttractionCommandMapper.toCommand;
import static org.mtech.infrastructure.adapter.inbound.rest.mapper.attraction.AttractionRemoveResponseMapper.toResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playsite")
public class AttractionController {

    private final AttractionAddUseCase attractionAddUseCase;
    private final AttractionRemoveUseCase attractionRemoveUseCase;
    private final AttractionsRemoveUseCase attractionsRemoveUseCase;

    @PostMapping("/{playsiteId}/attractions:add")
    public AttractionAddResponse addAttraction(
            @PathVariable Long playsiteId,
            @Valid @RequestBody AttractionAddRequest request
    ) {
        var result = attractionAddUseCase.invoke(toCommand(request, playsiteId));

        return switch (result) {
            case AttractionAdded a -> toResponse(a);
            case PlaysiteToAddAttractionNotFound ignored -> toEmptyResponse();
            case PlaysiteAttractionCapacityExceeded ignored -> toRejectedAddResponse();
        };
    }

    @DeleteMapping("/attractions:remove")
    public AttractionRemoveResponse removeAttraction(
            @Valid @RequestBody AttractionRemoveRequest request
    ) {
        var result = attractionRemoveUseCase.invoke(toCommand(request));

        return switch (result) {
            case AttractionRemoved a -> toResponse(a);
            case AttractionNotFound nf -> toResponse(nf);
        };
    }

    @DeleteMapping("/{playsiteId}/attractions:removeAll")
    public AttractionsRemoveResponse removeAllAttractions(@PathVariable Long playsiteId) {
        var result = attractionsRemoveUseCase.invoke(toCommand(playsiteId));

        return switch (result) {
            case AttractionsRemoved a -> toResponse(a.id().value());
            case PlaysiteToRemoveAttractionsNotFound ignored -> toResponse();
        };
    }

}
