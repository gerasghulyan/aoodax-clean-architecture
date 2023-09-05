package com.aoodax.platform.infrastructure.repository.tag.mapper;

import com.aoodax.platform.contract.model.tag.TagModel;
import com.aoodax.platform.infrastructure.domain.entity.organization.tag.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TagMapper {

    TagModel toModel(TagEntity entity);

    TagEntity toEntity(TagModel model);
}
