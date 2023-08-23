package com.aoodax.platform.infrastructure.domain.entity.organization.common;

import lombok.Builder;

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertHasTextParameterArgument;
import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertNotNullParameterArgument;

public record Address(String address, Coordinates coordinates) {

    @Builder
    public Address {
        assertHasTextParameterArgument(address, "address");
        assertNotNullParameterArgument(coordinates, "coordinates");
    }
}
    

  
