package com.aoodax.platform.contract.input.common;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertNotNullParameterArgument;

@EqualsAndHashCode
@ToString
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PaginationAwareDto {

    int page;
    int size;

    @Builder
    public PaginationAwareDto(final int page,
                              final int size) {
        assertNotNullParameterArgument(page, "page");
        assertNotNullParameterArgument(size, "size");
        
        this.page = page - 1;
        this.size = size;
    }
}