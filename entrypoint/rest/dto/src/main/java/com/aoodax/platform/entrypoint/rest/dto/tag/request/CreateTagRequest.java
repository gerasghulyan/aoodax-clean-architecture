package com.aoodax.platform.entrypoint.rest.dto.tag.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTagRequest {

    @JsonProperty
    @NotBlank
    @Size(min = 2, max = 255)
    String name;
}
