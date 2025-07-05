package org.mtech.adapter.inbound.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mtech.adapter.inbound.rest.request.PlaysiteAddRequest;
import org.mtech.adapter.inbound.rest.request.PlaysiteRemoveRequest;
import org.mtech.adapter.inbound.rest.response.PlaysiteAddResponse;
import org.mtech.adapter.inbound.rest.response.PlaysitesResponse;
import org.mtech.adapter.inbound.rest.response.PlaysiteRemoveResponse;
import org.mtech.core.playsite.get.PlaysitesQueryResult.Playsites;
import org.mtech.core.playsite.get.PlaysitesQueryResult.PlaysitesNotFound;
import org.mtech.core.playsite.get.PlaysitesQueryUseCase;
import org.springframework.web.bind.annotation.*;

import static org.mtech.adapter.inbound.rest.mapper.PlaysiteMapper.toEmptyResponse;
import static org.mtech.adapter.inbound.rest.mapper.PlaysiteMapper.toResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class PlaygroundController {

    private final PlaysitesQueryUseCase playsitesQueryUseCase;

    @GetMapping("/playsites")
    public PlaysitesResponse getPlaysites() {
        var result = playsitesQueryUseCase.invoke();

        return switch (result) {
            case PlaysitesNotFound ignored -> toEmptyResponse();
            case Playsites p -> toResponse(p);
        };
    }

    @PostMapping("/playsites:add")
    public PlaysiteAddResponse addPlaysite(@Valid @RequestBody PlaysiteAddRequest request) {
        return new PlaysiteAddResponse(1L);
    }

    @PostMapping("/playsites:remove")
    public PlaysiteRemoveResponse removePlaysite(@Valid @RequestBody PlaysiteRemoveRequest request) {
        return new PlaysiteRemoveResponse();
    }

}
