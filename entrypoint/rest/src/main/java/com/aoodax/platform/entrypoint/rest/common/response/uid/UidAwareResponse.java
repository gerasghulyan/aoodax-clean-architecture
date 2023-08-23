package com.aoodax.platform.entrypoint.rest.common.response.uid;

import com.aoodax.platform.entrypoint.rest.common.response.ResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class UidAwareResponse implements ResponseDto {

    @JsonProperty("uid")
    public String uid;
}
