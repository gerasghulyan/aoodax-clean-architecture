package com.aoodax.platform.infrastructure.domain.entity.organization.common;

import lombok.Builder;

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertNotNullParameterArgument;

public record Coordinates(Double lat, Double lng) {

    @Builder
    public Coordinates {
        assertNotNullParameterArgument(lat, "lat");
        assertNotNullParameterArgument(lng, "lng");
    }
}
