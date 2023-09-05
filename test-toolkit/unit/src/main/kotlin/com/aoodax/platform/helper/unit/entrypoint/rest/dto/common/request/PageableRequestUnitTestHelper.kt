package com.aoodax.platform.helper.unit.entrypoint.rest.dto.common.request

import com.aoodax.jvm.common.rest.dto.request.PageableRequest
import com.aoodax.jvm.common.test.toolkit.Randomizer.Companion.generateRandomInteger
import com.aoodax.platform.contract.input.common.PaginationAwareDto
import org.springframework.data.domain.PageRequest

class PageableRequestUnitTestHelper {

    companion object {

        fun buildPageableRequest(
            page: Int = generateRandomInteger(),
            size: Int = generateRandomInteger()
        ): PageableRequest = PageableRequest.builder().page(page).size(size).build()

        fun buildPaginationAwareDto(
            page: Int = generateRandomInteger(),
            size: Int = generateRandomInteger()
        ): PaginationAwareDto = PaginationAwareDto.builder().page(page).size(size).build()

        fun buildPageRequest(
            page: Int = generateRandomInteger(),
            size: Int = generateRandomInteger()
        ): PageRequest = PageRequest.of(page, size)
    }
}