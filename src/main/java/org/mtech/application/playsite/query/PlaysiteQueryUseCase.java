package org.mtech.application.playsite.query;

import lombok.RequiredArgsConstructor;
import org.mtech.infrastructure.adapter.outbound.database.repository.PlaysiteRepository;
import org.mtech.application.playsite.query.PlaysiteQueryResult.PlaysiteFound;
import org.mtech.application.playsite.query.PlaysiteQueryResult.PlaysiteNotFound;
import org.mtech.application.usecase.QueryUseCaseWithRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaysiteQueryUseCase implements QueryUseCaseWithRequest<PlaysiteQuery, PlaysiteQueryResult> {

    private final PlaysiteRepository repository;

    @Override
    public PlaysiteQueryResult invoke(PlaysiteQuery query) {
        var playsite = repository.findById(query.id());
        return playsite
                .<PlaysiteQueryResult>map(PlaysiteFound::new)
                .orElse(new PlaysiteNotFound(query.id()));
    }

}
