package com.aoodax.platform.helper.integration.tag

import com.aoodax.platform.contract.input.tag.CreateTagUseCase
import com.aoodax.platform.contract.input.tag.dto.CreateTagDto
import com.aoodax.platform.contract.model.tag.TagModel
import com.aoodax.platform.helper.unit.contract.dto.CreateTagDtoUnitTestHelper.Companion.buildCreateTagDto
import org.springframework.stereotype.Component

@Component
class TagIntegrationTestHelper(
    private val createTagUseCase: CreateTagUseCase
) {

    fun persistTag(
        dto: CreateTagDto? = buildCreateTagDto()
    ): TagModel {
        return createTagUseCase.create(dto)
    }
}