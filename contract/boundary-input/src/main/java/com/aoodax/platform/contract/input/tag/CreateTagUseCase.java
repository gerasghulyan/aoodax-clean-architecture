package com.aoodax.platform.contract.input.tag;

import com.aoodax.platform.contract.input.tag.dto.CreateTagDto;
import com.aoodax.platform.contract.model.tag.TagModel;

public interface CreateTagUseCase {

    TagModel create(CreateTagDto dto);

}
