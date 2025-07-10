package org.mtech.domain.vo;

import lombok.NonNull;

public record AttractionId(@NonNull Long value) {

    public AttractionId {
        if (value < 0) {
            throw new IllegalArgumentException(
                "AttractionId '%s' cannot be constructed, because value must be greater than or equal to 0"
                    .formatted(value)
            );
        }
    }

    public static AttractionId of(Long value) {
        return new AttractionId(value);
    }
}
