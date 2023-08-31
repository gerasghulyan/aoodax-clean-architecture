package com.aoodax.platform.entrypoint.rest.dto.tag.response;

import com.aoodax.jvm.common.rest.dto.response.grid.response.AbstractGridAwareResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class TagGridResponse extends AbstractGridAwareResponse<TagResponse> {
 
    @Builder
    public TagGridResponse(final List<TagResponse> data, final long total) {
        super(data, total);
    }
}
