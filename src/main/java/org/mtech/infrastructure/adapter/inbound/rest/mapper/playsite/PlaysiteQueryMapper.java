package org.mtech.infrastructure.adapter.inbound.rest.mapper.playsite;

import lombok.experimental.UtilityClass;
import org.mtech.application.playsite.query.PlaysiteQuery;
import org.mtech.domain.vo.PlaysiteId;

@UtilityClass
public class PlaysiteQueryMapper {

    public static PlaysiteQuery toQuery(Long id) {
        return PlaysiteQuery.builder()
                .id(PlaysiteId.of(id))
                .build();
    }

}
