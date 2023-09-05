package com.aoodax.platform.contract.input.output.tag;

import com.aoodax.platform.contract.model.tag.TagModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TagRepository {

    TagModel create(TagModel model);

    Optional<TagModel> getByUid(String uid);

    Optional<TagModel> getByName(String name);

    Optional<TagModel> markAsRemoved(String uid);

    Optional<TagModel> update(TagModel model);

    Page<TagModel> find(final Pageable pageable);
}
