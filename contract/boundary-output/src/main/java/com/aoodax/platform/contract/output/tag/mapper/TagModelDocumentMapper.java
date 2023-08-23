package com.aoodax.platform.contract.output.tag.mapper;

import com.aoodax.platform.contract.model.tag.TagModel;
import com.aoodax.platform.infrastructure.domain.entity.organization.tag.TagEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.aoodax.jvm.common.utils.validation.ParameterValidator.assertNotNullParameterArgument;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TagModelDocumentMapper {

    public static TagModel toModel(final TagEntity document) {
        assertNotNullParameterArgument(document, "TagDocument");

        return TagModel.builder()
                .uid(document.getUid())
                .name(document.getName())
                .build();
    }

    public static TagEntity toDocument(final TagModel model) {
        assertNotNullParameterArgument(model, "TagModel");

        return TagEntity.builder()
                .uid(model.getUid())
                .name(model.getName())
                .build();
    }

}
