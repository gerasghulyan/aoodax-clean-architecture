package com.aoodax.platform.contract.input.tag.dto;

import com.aoodax.jvm.common.utils.validation.ParameterValidator;
import com.aoodax.platform.contract.model.common.dto.ModelDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@EqualsAndHashCode
@ToString
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CreateTagDto implements ModelDto {

    String name;

    @Builder
    public CreateTagDto(final String name) {
        ParameterValidator.assertHasTextParameterArgument(name, "name");

        this.name = name;
    }
}
