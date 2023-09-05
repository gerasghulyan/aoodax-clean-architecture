package com.aoodax.platform.usecase.tag

import com.aoodax.jvm.common.test.toolkit.Randomizer
import com.aoodax.platform.contract.input.exception.NotFoundException
import com.aoodax.platform.contract.input.output.tag.TagRepository
import com.aoodax.platform.contract.input.tag.GetByIdTagUseCase
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

class GetByIdTagUseCaseUnitTest : AbstractUseCaseUnitTest() {

    private lateinit var getByIdTagUseCase: GetByIdTagUseCase

    @Mock
    private lateinit var tagRepository: TagRepository

    @BeforeEach
    fun before() {
        getByIdTagUseCase = GetByIdTagUseCaseImpl(tagRepository)
    }

    @Test
    fun `should throw an exception when param is invalid`() {
        assertThatThrownBy {
            getByIdTagUseCase.getById(null)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Empty string was passed as an argument for parameter 'uid'")

        assertThatThrownBy {
            getByIdTagUseCase.getById("")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Empty string was passed as an argument for parameter 'uid'")
    }

    @Test
    fun `should throw an exception when tag not found`() {
        val uid = Randomizer.generateRandomUid()

        whenever(tagRepository.getByUid(uid)).thenReturn(Optional.empty())

        assertThatThrownBy { getByIdTagUseCase.getById(uid) }
            .isExactlyInstanceOf(NotFoundException::class.java)

        verify(tagRepository, times(1)).getByUid(uid)
    }

    @Test
    fun `should successfully delete tag`() {
        val model = buildTagModel()

        whenever(tagRepository.getByUid(model.uid)).thenReturn(Optional.of(model))

        getByIdTagUseCase.getById(model.uid).let {
            assertThat(it).isNotNull()
            assertThat(it.uid).isEqualTo(model.uid)
            assertThat(it.name).isEqualTo(model.name)
        }

        verify(tagRepository, times(1)).getByUid(model.uid)
    }
}