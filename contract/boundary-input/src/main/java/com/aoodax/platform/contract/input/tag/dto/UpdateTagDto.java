package com.aoodax.platform.contract.input.tag.dto;

import lombok.Builder;

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertHasTextParameterArgument;

public record UpdateTagDto(String uid, String name) {

    @Builder
    public UpdateTagDto {
        assertHasTextParameterArgument(uid, "uid");
        assertHasTextParameterArgument(name, "name");
    }
}
