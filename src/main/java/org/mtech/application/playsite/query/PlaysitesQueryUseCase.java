package org.mtech.application.playsite.query;

import lombok.RequiredArgsConstructor;
import org.mtech.infrastructure.adapter.outbound.database.repository.PlaysiteRepository;
import org.mtech.application.playsite.query.PlaysitesQueryResult.PlaysitesNotFound;
import org.mtech.application.playsite.query.PlaysitesQueryResult.PlaysitesResult;
import org.mtech.application.usecase.QueryUseCase;
import org.mtech.domain.Playsite;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlaysitesQueryUseCase implements QueryUseCase<PlaysitesQueryResult> {

    private final PlaysiteRepository repository;

    @Override
    public PlaysitesQueryResult invoke() {
        List<Playsite> playsites = repository.findAll();
        return playsites.isEmpty() ?
                new PlaysitesNotFound() :
                new PlaysitesResult(playsites);
    }

}
