package org.mtech.core.playsite.get;

import lombok.RequiredArgsConstructor;
import org.mtech.adapter.outbound.database.repository.PlaysiteRepository;
import org.mtech.core.playsite.get.PlaysitesQueryResult.PlaysitesNotFound;
import org.mtech.core.playsite.get.PlaysitesQueryResult.Playsites;
import org.mtech.core.usecase.QueryUseCase;
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
                new Playsites(playsites);
    }

}
