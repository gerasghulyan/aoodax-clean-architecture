package com.aoodax.platform.entrypoint.rest.dto.tag.request

import com.aoodax.jvm.common.test.toolkit.Randomizer.Companion.generateRandomString
import com.aoodax.platform.entrypoint.rest.dto.AbstractEntryPointDtoUnitTest
import com.aoodax.platform.helper.unit.entrypoint.rest.dto.tag.request.TagRequestUnitTestHelper.Companion.buildCreateTagRequest
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class CreateTagRequestUnitTest : AbstractEntryPointDtoUnitTest() {

    @Test
    fun `should fail when name is blank`() {
        assertFalse(validator.validate(buildCreateTagRequest(name = null)).isEmpty())
        assertFalse(validator.validate(buildCreateTagRequest(name = "")).isEmpty())
        assertFalse(validator.validate(buildCreateTagRequest(name = generateRandomString(1))).isEmpty())
        assertFalse(validator.validate(buildCreateTagRequest(name = generateRandomString(256))).isEmpty())
    }
}