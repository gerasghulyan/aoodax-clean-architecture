package com.aoodax.platform.entrypoint.rest.controller.tag.response;

import com.aoodax.platform.entrypoint.rest.common.response.uid.UidAwareResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TagResponse extends UidAwareResponse {

    @JsonProperty
    String name;

    @Builder
    public TagResponse(final String uid,
                       final String name) {
        super(uid);
        this.name = name;
    }
}
