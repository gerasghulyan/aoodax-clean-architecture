package com.aoodax.platform.contract.input.tag.dto;

import com.aoodax.platform.contract.model.common.dto.ModelDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertHasTextParameterArgument;

@Getter
@EqualsAndHashCode
@ToString
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UpdateTagDto implements ModelDto {

    String uid;
    String name;

    @Builder
    public UpdateTagDto(final String uid,
                        final String name) {
        assertHasTextParameterArgument(uid, "uid");
        assertHasTextParameterArgument(name, "name");

        this.uid = uid;
        this.name = name;
    }
}
