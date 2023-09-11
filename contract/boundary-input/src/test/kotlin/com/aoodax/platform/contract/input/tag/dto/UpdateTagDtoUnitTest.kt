package com.aoodax.platform.contract.input.tag.dto

import com.aoodax.jvm.common.test.toolkit.Randomizer
import com.aoodax.platform.contract.input.AbstractContractUnitTest
import com.aoodax.platform.helper.unit.contract.dto.UpdateTagDtoUnitTestHelper.Companion.buildUpdateTagDto
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class UpdateTagDtoUnitTest : AbstractContractUnitTest() {

    @Test
    fun `should throw an exception when uid is blank`() {
        assertThatThrownBy {
            buildUpdateTagDto(uid = null)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Empty string was passed as an argument for parameter 'uid'")

        assertThatThrownBy {
            buildUpdateTagDto(uid = "")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Empty string was passed as an argument for parameter 'uid'")
    }

    @Test
    fun `should throw an exception when name is blank`() {
        assertThatThrownBy {
            buildUpdateTagDto(name = null)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Empty string was passed as an argument for parameter 'name'")

        assertThatThrownBy {
            buildUpdateTagDto(name = "")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Empty string was passed as an argument for parameter 'name'")
    }

    @Test
    fun `should success`() {
        val uid = Randomizer.generateRandomUid()
        val name = Randomizer.generateRandomString()
        buildUpdateTagDto(name = name, uid = uid).let {
            assertThat(it).isNotNull
            assertThat(it.uid()).isNotBlank().isEqualTo(uid)
            assertThat(it.name()).isNotBlank().isEqualTo(name)
        }
    }
}