package com.aoodax.platform.entrypoint.rest.dto.tag.request;

import com.aoodax.jvm.common.rest.dto.request.PageableRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class FindTagsRequest extends PageableRequest {
    
}
