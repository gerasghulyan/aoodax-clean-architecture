package com.aoodax.platform.contract.input.tag.dto

import com.aoodax.jvm.common.test.toolkit.Randomizer
import com.aoodax.platform.contract.input.AbstractContractUnitTest
import com.aoodax.platform.helper.unit.contract.dto.CreateTagDtoUnitTestHelper.Companion.buildCreateTagDto
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class CreateTagDtoUnitTest : AbstractContractUnitTest() {

    @Test
    fun `should throw an exception when name is blank`() {
        assertThatThrownBy {
            buildCreateTagDto(name = null)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Empty string was passed as an argument for parameter 'name'")

        assertThatThrownBy {
            buildCreateTagDto(name = "")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Empty string was passed as an argument for parameter 'name'")
    }

    @Test
    fun `should success`() {
        val name = Randomizer.generateRandomString()
        buildCreateTagDto(name = name).let {
            assertThat(it).isNotNull
            assertThat(it.name()).isNotBlank().isEqualTo(name)
        }
    }
}