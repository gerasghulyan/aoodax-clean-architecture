package com.aoodax.platform.contract.input.tag

import com.aoodax.jvm.common.test.toolkit.Randomizer
import com.aoodax.platform.contract.input.AbstractModelUnitTest
import com.aoodax.platform.helper.unit.contract.dto.UpdateTagDtoUnitTestHelper.Companion.buildUpdateTagDto
import com.aoodax.platform.helper.unit.contract.model.TagModelUnitTestHelper.Companion.buildTagModel
import com.aoodax.platform.helper.unit.contract.model.TagModelUnitTestHelper.Companion.buildTagModelForCreation
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class TagModelUnitTest : AbstractModelUnitTest() {

    @Test
    fun `test builderForCreation should throw an exception when name is blank`() {
        assertThatThrownBy {
            buildTagModelForCreation(name = null)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Empty string was passed as an argument for parameter 'name'")

        assertThatThrownBy {
            buildTagModelForCreation(name = "")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Empty string was passed as an argument for parameter 'name'")
    }

    @Test
    fun `test builderForCreation should success`() {
        val name = Randomizer.generateRandomString()
        buildTagModelForCreation(name = name).let {
            assertThat(it).isNotNull
            assertThat(it.uid).isNotBlank()
            assertThat(it.name).isNotBlank().isEqualTo(name)
        }
    }

    @Test
    fun `test builder should throw an exception when uid is blank`() {
        assertThatThrownBy {
            buildTagModel(uid = null)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Empty string was passed as an argument for parameter 'uid'")

        assertThatThrownBy {
            buildTagModel(uid = "")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Empty string was passed as an argument for parameter 'uid'")
    }

    @Test
    fun `test builder should throw an exception when name is blank`() {
        assertThatThrownBy {
            buildTagModel(name = null)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Empty string was passed as an argument for parameter 'name'")

        assertThatThrownBy {
            buildTagModel(name = "")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Empty string was passed as an argument for parameter 'name'")
    }

    @Test
    fun `test builder should success`() {
        val uid = Randomizer.generateRandomUid()
        val name = Randomizer.generateRandomString()
        buildTagModel(uid = uid, name = name).let {
            assertThat(it).isNotNull
            assertThat(it.uid).isNotBlank().isEqualTo(uid)
            assertThat(it.name).isNotBlank().isEqualTo(name)
        }
    }

    @Test
    fun `test update should throw an exception when dto is blank`() {
        val model = buildTagModel()
        assertThatThrownBy {
            model.update(null)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Empty string was passed as an argument for parameter 'name'")
    }

    @Test
    fun `test update should success`() {
        val model = buildTagModel()
        val dto = buildUpdateTagDto()
        model.update(dto.name()).let {
            assertThat(model.name).isNotBlank().isEqualTo(dto.name())
        }
    }
}