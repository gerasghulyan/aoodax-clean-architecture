package com.aoodax.platform.usecase.tag

import com.aoodax.platform.contract.input.exception.AlreadyExistsException
import com.aoodax.platform.contract.input.output.tag.TagRepository
import com.aoodax.platform.contract.input.tag.CreateTagUseCase
import com.aoodax.platform.helper.unit.contract.dto.CreateTagDtoUnitTestHelper.Companion.buildCreateTagDto
import com.aoodax.platform.helper.unit.contract.model.TagModelUnitTestHelper.Companion.buildTagModel
import com.aoodax.platform.usecase.AbstractUseCaseUnitTest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.*

class CreateTagUseCaseUnitTest : AbstractUseCaseUnitTest() {

    private lateinit var createTagUseCase: CreateTagUseCase

    @Mock
    private lateinit var tagRepository: TagRepository

    @BeforeEach
    fun before() {
        createTagUseCase = CreateTagUseCaseImpl(tagRepository)
    }

    @Test
    fun `should throw an exception when param is null`() {
        assertThatThrownBy {
            createTagUseCase.create(null)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `should throw an exception when tag exists for name`() {
        val dto = buildCreateTagDto()
        val model = buildTagModel()

        whenever(tagRepository.getByName(dto.name)).thenReturn(Optional.of(model))

        assertThatThrownBy { createTagUseCase.create(dto) }
            .isExactlyInstanceOf(AlreadyExistsException::class.java)

        verify(tagRepository, times(1)).getByName(dto.name)
    }

    @Test
    fun `should successfully create tag`() {
        val dto = buildCreateTagDto()
        val model = buildTagModel(name = dto.name)

        whenever(tagRepository.getByName(dto.name)).thenReturn(Optional.empty())
        whenever(tagRepository.create(any())).thenReturn(model)

        createTagUseCase.create(dto).let {
            assertThat(it).isNotNull()
            assertThat(it.uid).isEqualTo(model.uid)
            assertThat(it.name).isEqualTo(model.name)
        }

        verify(tagRepository, times(1)).getByName(dto.name)
        verify(tagRepository, times(1)).create(any())
    }
}