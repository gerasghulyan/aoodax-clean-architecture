package com.aoodax.platform.contract.model.tag;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertNotNullParameterArgument;

@Getter
@EqualsAndHashCode
public class TagModel {

    private final String uid;
    private final String name;

    @Builder
    public TagModel(
            final String uid,
            final String name) {
        assertNotNullParameterArgument(uid, "uid");
        assertNotNullParameterArgument(name, "name");
        this.uid = uid;
        this.name = name;
    }

    @Builder(builderMethodName = "builderForCreation", builderClassName = "BuilderForCreation")
    public TagModel(final String name) {
        assertNotNullParameterArgument(name, "name");
        this.uid = UUID.randomUUID().toString();
        this.name = name;
    }
}
