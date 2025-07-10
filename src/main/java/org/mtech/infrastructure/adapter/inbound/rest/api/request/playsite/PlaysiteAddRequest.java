package org.mtech.infrastructure.adapter.inbound.rest.api.request.playsite;

import org.mtech.infrastructure.adapter.inbound.rest.api.request.attraction.AttractionAddRequest.RequestAttraction;

import java.util.List;

public record PlaysiteAddRequest(
         List<RequestAttraction> attractions
) {}
