package com.aoodax.platform.contract.input.tag.dto;

import com.aoodax.jvm.common.utils.validation.ParameterValidator;
import lombok.Builder;

public record CreateTagDto(String name) {

    @Builder
    public CreateTagDto {
        ParameterValidator.assertHasTextParameterArgument(name, "name");
    }
}
