package com.aoodax.platform.infrastructure.domain.entity.organization.common;

import lombok.Builder;

@Builder
public record Content(
        String alias,
        String name,
        Status status,
        String description) {

}
    