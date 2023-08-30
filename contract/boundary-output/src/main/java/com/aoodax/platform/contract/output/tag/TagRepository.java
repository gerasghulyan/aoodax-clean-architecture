package com.aoodax.platform.contract.output.tag;

import com.aoodax.platform.contract.model.tag.TagModel;
import com.aoodax.platform.infrastructure.domain.entity.organization.tag.TagEntity;

import java.util.Optional;

public interface TagRepository {

    TagEntity create(TagModel model);
    
    Optional<TagEntity> getByUid(String uid);

    Optional<TagEntity> getByName(String name);

    Optional<TagEntity> markAsRemoved(String uid);
    
    Optional<TagEntity> update(TagModel model);
}
