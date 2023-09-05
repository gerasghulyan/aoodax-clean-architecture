package com.aoodax.platform.entrypoint.rest.dto.tag.request

import com.aoodax.jvm.common.test.toolkit.Randomizer.Companion.generateRandomString
import com.aoodax.platform.entrypoint.rest.dto.AbstractEntryPointDtoUnitTest
import com.aoodax.platform.helper.unit.entrypoint.rest.dto.tag.request.TagRequestUnitTestHelper.Companion.buildUpdateTagRequest
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class UpdateTagRequestUnitTest : AbstractEntryPointDtoUnitTest() {

    @Test
    fun `should fail when name is blank`() {
        assertFalse(validator.validate(buildUpdateTagRequest(name = null)).isEmpty())
        assertFalse(validator.validate(buildUpdateTagRequest(name = "")).isEmpty())
        assertFalse(validator.validate(buildUpdateTagRequest(name = generateRandomString(1))).isEmpty())
        assertFalse(validator.validate(buildUpdateTagRequest(name = generateRandomString(256))).isEmpty())
    }
}