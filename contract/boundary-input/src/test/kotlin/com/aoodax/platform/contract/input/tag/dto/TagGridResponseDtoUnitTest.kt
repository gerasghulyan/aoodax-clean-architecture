package com.aoodax.platform.contract.input.tag.dto

import com.aoodax.jvm.common.test.toolkit.Randomizer
import com.aoodax.platform.contract.input.AbstractContractUnitTest
import com.aoodax.platform.helper.unit.contract.dto.TagGridResponseDtoUnitTestHelper.Companion.buildTagGridResponseDto
import com.aoodax.platform.helper.unit.contract.model.TagModelUnitTestHelper
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class TagGridResponseDtoUnitTest : AbstractContractUnitTest() {

    @Test
    fun `should throw an exception when name is blank`() {
        assertThatThrownBy {
            buildTagGridResponseDto(items = null)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Null was passed as an argument for parameter 'items'")
    }

    @Test
    fun `should success`() {
        val items = listOf(TagModelUnitTestHelper.buildTagModel(), TagModelUnitTestHelper.buildTagModel())
        val total = Randomizer.generateRandomLong()
        buildTagGridResponseDto(items, total).let {
            assertThat(it).isNotNull
            assertThat(it.total).isEqualTo(total)
            assertThat(it.items).isEqualTo(items)
        }
    }
}