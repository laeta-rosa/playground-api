package org.mtech.infrastructure.adapter.inbound.rest.mapper.kid;

import lombok.experimental.UtilityClass;
import org.mtech.application.kid.count.KidsCountQueryResult.KidsCountCalculated;
import org.mtech.infrastructure.adapter.inbound.rest.api.response.kid.KidsTotalCountResponse;

@UtilityClass
public class KidCountResponseMapper {

    public static KidsTotalCountResponse toResponse(KidsCountCalculated result) {
        return KidsTotalCountResponse.builder()
                .count(result.count())
                .build();
    }

}
