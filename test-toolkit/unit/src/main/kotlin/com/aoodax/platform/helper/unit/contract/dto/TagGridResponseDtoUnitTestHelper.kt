package com.aoodax.platform.helper.unit.contract.dto

import com.aoodax.jvm.common.test.toolkit.Randomizer
import com.aoodax.platform.contract.input.tag.dto.TagGridResponseDto
import com.aoodax.platform.contract.model.tag.TagModel
import com.aoodax.platform.helper.unit.contract.model.TagModelUnitTestHelper.Companion.buildTagModel

class TagGridResponseDtoUnitTestHelper {

    companion object {

        fun buildTagGridResponseDto(
            items: List<TagModel>? = listOf(buildTagModel(), buildTagModel()),
            total: Long = Randomizer.generateRandomLong()
        ): TagGridResponseDto = TagGridResponseDto.builder()
            .items(items)
            .total(total)
            .build()
    }
}