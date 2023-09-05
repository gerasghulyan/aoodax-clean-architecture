package com.aoodax.platform.helper.unit.contract.dto

import com.aoodax.jvm.common.test.toolkit.Randomizer.Companion.generateRandomString
import com.aoodax.jvm.common.test.toolkit.Randomizer.Companion.generateRandomUid
import com.aoodax.platform.contract.input.tag.dto.UpdateTagDto

class UpdateTagDtoUnitTestHelper {

    companion object {

        fun buildUpdateTagDto(
            uid: String? = generateRandomUid(),
            name: String? = generateRandomString()
        ): UpdateTagDto = UpdateTagDto.builder().uid(uid).name(name).build()
    }
}