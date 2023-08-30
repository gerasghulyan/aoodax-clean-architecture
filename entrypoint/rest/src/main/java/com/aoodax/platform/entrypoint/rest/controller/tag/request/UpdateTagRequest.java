package com.aoodax.platform.entrypoint.rest.controller.tag.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateTagRequest {

    @JsonProperty
    @NotBlank
    String name;
}
