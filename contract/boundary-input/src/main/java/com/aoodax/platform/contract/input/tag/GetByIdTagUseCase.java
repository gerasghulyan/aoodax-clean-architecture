package com.aoodax.platform.contract.input.tag;

import com.aoodax.platform.contract.model.tag.TagModel;

public interface GetByIdTagUseCase {

    TagModel getById(String uid);

}
