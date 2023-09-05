package com.aoodax.platform.usecase.tag

import com.aoodax.platform.contract.input.exception.NotFoundException
import com.aoodax.platform.contract.input.output.tag.TagRepository
import com.aoodax.platform.contract.input.tag.UpdateTagUseCase
import com.aoodax.platform.helper.unit.contract.dto.UpdateTagDtoUnitTestHelper.Companion.buildUpdateTagDto
import com.aoodax.platform.helper.unit.contract.model.TagModelUnitTestHelper.Companion.buildTagModel
import com.aoodax.platform.usecase.AbstractUseCaseUnitTest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.*

class UpdateTagUseCaseUnitTest : AbstractUseCaseUnitTest() {

    private lateinit var updateTagUseCase: UpdateTagUseCase

    @Mock
    private lateinit var tagRepository: TagRepository

    @BeforeEach
    fun before() {
        updateTagUseCase = UpdateTagUseCaseImpl(tagRepository)
    }

    @Test
    fun `should throw an exception when param is null`() {
        assertThatThrownBy {
            updateTagUseCase.update(null)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Null was passed as an argument for parameter 'dto'")
    }

    @Test
    fun `should throw an exception when tag not found`() {
        val dto = buildUpdateTagDto()

        whenever(tagRepository.getByUid(dto.uid)).thenReturn(Optional.empty())

        assertThatThrownBy { updateTagUseCase.update(dto) }
            .isExactlyInstanceOf(NotFoundException::class.java)

        verify(tagRepository, times(1)).getByUid(dto.uid)
    }

    @Test
    fun `should successfully update tag`() {
        val dto = buildUpdateTagDto()
        val model = buildTagModel()

        whenever(tagRepository.getByUid(dto.uid)).thenReturn(Optional.of(model))
        whenever(tagRepository.update(model)).thenReturn(Optional.of(model))

        updateTagUseCase.update(dto).let {
            assertThat(it).isNotNull()
            assertThat(it.uid).isEqualTo(model.uid)
            assertThat(it.name).isEqualTo(model.name)
        }

        verify(tagRepository, times(1)).getByUid(dto.uid)
        verify(tagRepository, times(1)).update(model)
    }
}