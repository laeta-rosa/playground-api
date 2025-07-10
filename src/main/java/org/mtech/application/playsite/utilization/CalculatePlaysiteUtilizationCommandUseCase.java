package org.mtech.application.playsite.utilization;

import lombok.RequiredArgsConstructor;
import org.mtech.application.playsite.query.PlaysiteQuery;
import org.mtech.application.usecase.QueryUseCaseWithRequest;
import org.mtech.domain.Playsite;
import org.mtech.domain.vo.PlaysiteUtilization;
import org.mtech.infrastructure.adapter.outbound.database.repository.PlaysiteRepository;
import org.springframework.stereotype.Component;

import org.mtech.application.playsite.utilization.CalculatePlaysiteUtilizationCommandResult.PlaysiteToCalculateUtilizationNotFound;
import org.mtech.application.playsite.utilization.CalculatePlaysiteUtilizationCommandResult.UtilizationCalculated;

import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;

@Component
@RequiredArgsConstructor
public class CalculatePlaysiteUtilizationCommandUseCase implements QueryUseCaseWithRequest<PlaysiteQuery, CalculatePlaysiteUtilizationCommandResult> {

    private final PlaysiteRepository repository;

    @Override
    public CalculatePlaysiteUtilizationCommandResult invoke(PlaysiteQuery query) {
        var playsite = repository.findById(query.id().value());
        return playsite
                .map(this::calculateUtilization)
                .orElse(new PlaysiteToCalculateUtilizationNotFound(query.id()));
    }

    private CalculatePlaysiteUtilizationCommandResult calculateUtilization(Playsite playsite) {
        var capacity = playsite.getCapacity();
        var occupancy = playsite.getPlayingKids().size();

        var utilization = capacity == 0 ?
                BigDecimal.ZERO :
                BigDecimal.valueOf(occupancy)
                        .divide(BigDecimal.valueOf(capacity), 4, HALF_UP)
                        .multiply(BigDecimal.valueOf(100));

        return new UtilizationCalculated(PlaysiteUtilization.of(utilization));
    }

}
