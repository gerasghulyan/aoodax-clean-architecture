package com.aoodax.platform.contract.input.tag;

import com.aoodax.platform.contract.input.common.PaginationAwareDto;
import com.aoodax.platform.contract.input.tag.dto.TagGridResponseDto;

public interface FindTagsUseCase {

    TagGridResponseDto find(final PaginationAwareDto paginationAwareDto);
}
