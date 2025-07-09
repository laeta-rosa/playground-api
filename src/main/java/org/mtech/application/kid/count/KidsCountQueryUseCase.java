package org.mtech.application.kid.count;

import lombok.RequiredArgsConstructor;
import org.mtech.application.usecase.QueryUseCase;
import org.mtech.infrastructure.adapter.outbound.database.repository.KidRepository;
import org.mtech.application.kid.count.KidsCountQueryResult.KidsCountCalculated;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KidsCountQueryUseCase implements QueryUseCase<KidsCountQueryResult> {

    private final KidRepository repository;

    @Override
    public KidsCountQueryResult invoke() {
        var totalKidsCount = repository.count();
        return new KidsCountCalculated(totalKidsCount);
    }

}
