package com.aoodax.platform.contract.input.tag;

import com.aoodax.platform.contract.input.tag.dto.UpdateTagDto;
import com.aoodax.platform.contract.model.tag.TagModel;

public interface UpdateTagUseCase {

    TagModel update(UpdateTagDto dto);

}
