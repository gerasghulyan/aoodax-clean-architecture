package com.aoodax.platform.usecase.tag

import com.aoodax.jvm.common.test.toolkit.Randomizer
import com.aoodax.platform.contract.input.exception.NotFoundException
import com.aoodax.platform.contract.input.output.tag.TagRepository
import com.aoodax.platform.contract.input.tag.DeleteTagUseCase
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

class DeleteTagUseCaseUnitTest : AbstractUseCaseUnitTest() {

    private lateinit var deleteTagUseCase: DeleteTagUseCase

    @Mock
    private lateinit var tagRepository: TagRepository

    @BeforeEach
    fun before() {
        deleteTagUseCase = DeleteTagUseCaseImpl(tagRepository)
    }

    @Test
    fun `should throw an exception when param is null`() {
        assertThatThrownBy {
            deleteTagUseCase.delete(null)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Empty string was passed as an argument for parameter 'uid'")
    }

    @Test
    fun `should throw an exception when tag not found`() {
        val uid = Randomizer.generateRandomUid()

        whenever(tagRepository.markAsRemoved(uid)).thenReturn(Optional.empty())

        assertThatThrownBy { deleteTagUseCase.delete(uid) }
            .isExactlyInstanceOf(NotFoundException::class.java)

        verify(tagRepository, times(1)).markAsRemoved(uid)
    }

    @Test
    fun `should successfully delete tag`() {
        val model = buildTagModel()

        whenever(tagRepository.markAsRemoved(model.uid)).thenReturn(Optional.of(model))

        deleteTagUseCase.delete(model.uid).let {
            assertThat(it).isNotNull()
            assertThat(it.uid).isEqualTo(model.uid)
            assertThat(it.name).isEqualTo(model.name)
        }

        verify(tagRepository, times(1)).markAsRemoved(model.uid)
    }
}