package org.mtech.infrastructure.adapter.inbound.rest.api.request.playsite;

import org.mtech.infrastructure.adapter.inbound.rest.api.request.attraction.AttractionAddRequest.RequestAttraction;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record PlaysiteAddRequest(
         List<RequestAttraction> attractions
) {}
