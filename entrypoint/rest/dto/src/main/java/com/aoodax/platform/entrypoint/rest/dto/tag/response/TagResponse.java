package com.aoodax.platform.entrypoint.rest.dto.tag.response;

import com.aoodax.jvm.common.rest.dto.response.uid.UidAwareResponse;
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
