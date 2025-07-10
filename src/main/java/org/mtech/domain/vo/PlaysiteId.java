package org.mtech.domain.vo;

import lombok.NonNull;

public record PlaysiteId(@NonNull Long value) {

    public PlaysiteId {
        if (value < 0) {
            throw new IllegalArgumentException(
                "PlaysiteId '%s' cannot be constructed, because value must be greater than or equal to 0"
                    .formatted(value)
            );
        }
    }

    public static PlaysiteId of(Long value) {
        return new PlaysiteId(value);
    }
}
