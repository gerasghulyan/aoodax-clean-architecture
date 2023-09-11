package com.aoodax.platform.contract.model.tag;

import com.aoodax.platform.contract.model.common.Default;
import com.aoodax.platform.contract.model.common.Model;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertHasTextParameterArgument;

@Getter
@EqualsAndHashCode
@ToString
public class TagModel implements Model {

    private final String uid;
    private String name;

    @Builder
    @Default
    public TagModel(
            final String uid,
            final String name) {
        assertHasTextParameterArgument(uid, "uid");
        assertName(name);

        this.uid = uid;
        this.name = name;
    }

    @Builder(builderMethodName = "builderForCreation", builderClassName = "BuilderForCreation")
    public TagModel(final String name) {
        assertName(name);

        this.uid = UUID.randomUUID().toString();
        this.name = name;
    }

    public void update(final String name) {
        assertName(name);

        this.name = name;
    }

    private void assertName(final String name) {
        assertHasTextParameterArgument(name, "name");
    }
}
