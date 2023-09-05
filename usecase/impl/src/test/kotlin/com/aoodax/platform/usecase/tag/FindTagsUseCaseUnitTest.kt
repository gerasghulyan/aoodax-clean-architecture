package com.aoodax.platform.usecase.tag

import com.aoodax.platform.contract.input.output.tag.TagRepository
import com.aoodax.platform.contract.input.tag.FindTagsUseCase
import com.aoodax.platform.helper.unit.contract.model.TagModelUnitTestHelper.Companion.buildTagModel
import com.aoodax.platform.helper.unit.entrypoint.rest.dto.common.request.PageableRequestUnitTestHelper.Companion.buildPageRequest
import com.aoodax.platform.helper.unit.entrypoint.rest.dto.common.request.PageableRequestUnitTestHelper.Companion.buildPaginationAwareDto
import com.aoodax.platform.usecase.AbstractUseCaseUnitTest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl

class FindTagsUseCaseUnitTest : AbstractUseCaseUnitTest() {

    private lateinit var findTagsUseCase: FindTagsUseCase

    @Mock
    private lateinit var tagRepository: TagRepository

    @BeforeEach
    fun before() {
        findTagsUseCase = FindTagsUseCaseImpl(tagRepository)
    }

    @Test
    fun `should throw an exception when param is null`() {
        assertThatThrownBy {
            findTagsUseCase.find(null)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Null was passed as an argument for parameter 'pagination'")
    }

    @Test
    fun `should successfully find tags when not found`() {
        val dto = buildPaginationAwareDto()
        val pageRequest = buildPageRequest(page = dto.page, size = dto.size)

        whenever(tagRepository.find(pageRequest)).thenReturn(Page.empty())

        findTagsUseCase.find(dto).let {
            assertThat(it).isNotNull
            assertThat(it.items).isEmpty()
            assertThat(it.total).isEqualTo(0)
        }

        verify(tagRepository, times(1)).find(pageRequest)
    }

    @Test
    fun `should successfully find tags`() {
        val dto = buildPaginationAwareDto()
        val pageRequest = buildPageRequest(page = dto.page, size = dto.size)
        val models = listOf(buildTagModel(), buildTagModel(), buildTagModel())

        whenever(tagRepository.find(pageRequest)).thenReturn(PageImpl(models))

        findTagsUseCase.find(dto).let {
            assertThat(it).isNotNull
            assertThat(it.items).isEqualTo(models)
            assertThat(it.total).isEqualTo(models.size.toLong())
        }

        verify(tagRepository, times(1)).find(pageRequest)
    }
}