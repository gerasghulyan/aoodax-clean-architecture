package com.aoodax.platform.contract.input.common;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertHasTextParameterArgument;

@Data
@EqualsAndHashCode
@ToString
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ContentAwareDto {

    String alias;
    String name;
    StatusDto status;
    String description;

    @Builder(setterPrefix = "with")
    public ContentAwareDto(
            final String alias,
            final String name,
            final StatusDto status,
            final String description) {
        assertHasTextParameterArgument(alias, "alias");
        assertHasTextParameterArgument(name, "name");
        assertHasTextParameterArgument(description, "description");
        this.alias = alias;
        this.name = name;
        this.status = status;
        this.description = description;
    }
}
