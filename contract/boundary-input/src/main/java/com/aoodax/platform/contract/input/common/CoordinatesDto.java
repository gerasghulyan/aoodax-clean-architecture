package com.aoodax.platform.contract.input.common;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertNotNullParameterArgument;

@Getter
@EqualsAndHashCode
@ToString
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CoordinatesDto {

    Double lat;
    Double lng;

    @Builder(setterPrefix = "with")
    public CoordinatesDto(final Double lat,
                          final Double lng) {
        assertNotNullParameterArgument(lat, "lat");
        assertNotNullParameterArgument(lng, "lng");
        this.lat = lat;
        this.lng = lng;
    }
}
