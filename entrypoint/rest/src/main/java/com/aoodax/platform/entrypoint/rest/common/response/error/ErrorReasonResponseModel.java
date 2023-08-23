package com.aoodax.platform.entrypoint.rest.common.response.error;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ErrorReasonResponseModel {

    private final Integer code;
    private final String message;

}
