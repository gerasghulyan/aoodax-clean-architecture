package com.aoodax.platform.entrypoint.rest.common.response.grid;

import com.aoodax.platform.entrypoint.rest.common.response.ResponseDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertNotNullParameterArgument;

@Data
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public abstract class AbstractGridAwareResponse<T extends ResponseDto> {

    List<T> items;
    Integer total;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    protected AbstractGridAwareResponse(final List<T> items,
                                        final Integer total) {
        assertNotNullParameterArgument(items, "items");
        assertNotNullParameterArgument(total, "total");

        this.items = items;
        this.total = total;
    }
}