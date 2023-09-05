package com.aoodax.platform.helper.unit.entrypoint.rest.dto.tag.request

import com.aoodax.jvm.common.test.toolkit.Randomizer.Companion.generateRandomString
import com.aoodax.platform.entrypoint.rest.dto.tag.request.CreateTagRequest
import com.aoodax.platform.entrypoint.rest.dto.tag.request.UpdateTagRequest

class TagRequestUnitTestHelper {

    companion object {

        fun buildCreateTagRequest(
            name: String? = generateRandomString()
        ): CreateTagRequest = CreateTagRequest(
            name
        )

        fun buildUpdateTagRequest(
            name: String? = generateRandomString()
        ): UpdateTagRequest = UpdateTagRequest(
            name
        )
    }
}