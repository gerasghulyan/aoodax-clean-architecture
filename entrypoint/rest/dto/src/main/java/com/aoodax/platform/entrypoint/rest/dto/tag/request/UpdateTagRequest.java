package com.aoodax.platform.entrypoint.rest.dto.tag.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateTagRequest(
        @NotBlank
        @Size(min = 2, max = 255)
        String name) {
}