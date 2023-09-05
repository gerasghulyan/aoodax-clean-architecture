package com.aoodax.platform.helper.unit.contract.dto

import com.aoodax.jvm.common.test.toolkit.Randomizer.Companion.generateRandomString
import com.aoodax.platform.contract.input.tag.dto.CreateTagDto

class CreateTagDtoUnitTestHelper {

    companion object {

        fun buildCreateTagDto(
            name: String? = generateRandomString()
        ): CreateTagDto = CreateTagDto.builder().name(name).build()
    }
}