package com.aoodax.platform.contract.input.common;

import com.aoodax.platform.contract.model.common.Model;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertNotNullParameterArgument;

@Data
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public abstract class AbstractGridAwareResponseDto<T extends Model> implements Model {

    List<T> items;
    long total;

    protected AbstractGridAwareResponseDto(final List<T> items,
                                           final long total) {
        assertNotNullParameterArgument(items, "items");

        this.items = items;
        this.total = total;
    }
}