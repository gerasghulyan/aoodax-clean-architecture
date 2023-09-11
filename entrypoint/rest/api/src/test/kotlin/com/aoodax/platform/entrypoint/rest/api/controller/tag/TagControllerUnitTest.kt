package com.aoodax.platform.entrypoint.rest.api.controller.tag

import com.aoodax.jvm.common.test.toolkit.Randomizer
import com.aoodax.platform.contract.input.tag.*
import com.aoodax.platform.entrypoint.rest.api.controller.AbstractEntryPointApiUnitTest
import com.aoodax.platform.helper.unit.contract.dto.CreateTagDtoUnitTestHelper.Companion.buildCreateTagDto
import com.aoodax.platform.helper.unit.contract.dto.TagGridResponseDtoUnitTestHelper.Companion.buildTagGridResponseDto
import com.aoodax.platform.helper.unit.contract.dto.UpdateTagDtoUnitTestHelper.Companion.buildUpdateTagDto
import com.aoodax.platform.helper.unit.contract.model.TagModelUnitTestHelper.Companion.buildCreateTagModel
import com.aoodax.platform.helper.unit.contract.model.TagModelUnitTestHelper.Companion.buildTagModel
import com.aoodax.platform.helper.unit.entrypoint.rest.dto.common.request.PageableRequestUnitTestHelper.Companion.buildPageableRequest
import com.aoodax.platform.helper.unit.entrypoint.rest.dto.common.request.PageableRequestUnitTestHelper.Companion.buildPaginationAwareDto
import com.aoodax.platform.helper.unit.entrypoint.rest.dto.tag.request.TagRequestUnitTestHelper.Companion.buildCreateTagRequest
import com.aoodax.platform.helper.unit.entrypoint.rest.dto.tag.request.TagRequestUnitTestHelper.Companion.buildUpdateTagRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class TagControllerUnitTest : AbstractEntryPointApiUnitTest() {

    private lateinit var tagController: TagController

    @Mock
    private lateinit var createTagUseCase: CreateTagUseCase

    @Mock
    private lateinit var getByIdTagUseCase: GetByIdTagUseCase

    @Mock
    private lateinit var findTagsUseCase: FindTagsUseCase

    @Mock
    private lateinit var updateTagUseCase: UpdateTagUseCase

    @Mock
    private lateinit var deleteTagUseCase: DeleteTagUseCase

    @BeforeEach
    fun before() {
        tagController = TagController(
            createTagUseCase, getByIdTagUseCase, findTagsUseCase, updateTagUseCase, deleteTagUseCase
        )
    }

    @Test
    fun `should successfully create tag`() {
        val request = buildCreateTagRequest()
        val dto = buildCreateTagDto(name = request.name)
        val model = buildCreateTagModel(name = dto.name)

        whenever(createTagUseCase.create(dto)).thenReturn(model)

        tagController.create(request).let {
            assertThat(it.uid).isNotBlank()
            assertThat(it.name).isEqualTo(request.name)
        }

        verify(createTagUseCase, times(1)).create(dto)
    }

    @Test
    fun `should successfully get tag by uid`() {
        val uid = Randomizer.generateRandomUid()
        val model = buildTagModel()

        whenever(getByIdTagUseCase.getById(uid)).thenReturn(model)

        tagController.getByUid(uid).let {
            assertThat(it.uid).isNotBlank()
            assertThat(it.name).isEqualTo(model.name)
        }

        verify(getByIdTagUseCase, times(1)).getById(uid)
    }

    @Test
    fun `should successfully update tag`() {
        val uid = Randomizer.generateRandomUid()
        val request = buildUpdateTagRequest()
        val dto = buildUpdateTagDto(uid = uid, name = request.name)
        val model = buildTagModel(name = dto.name)

        whenever(updateTagUseCase.update(dto)).thenReturn(model)

        tagController.update(uid, request).let {
            assertThat(it.uid).isNotBlank()
            assertThat(it.name).isEqualTo(request.name)
        }

        verify(updateTagUseCase, times(1)).update(dto)
    }

    @Test
    fun `should successfully find tags`() {
        val request = buildPageableRequest(page = 1, size = 3)
        val dto = buildPaginationAwareDto(page = request.page - 1, size = request.size)
        val models = listOf(buildTagModel(), buildTagModel(), buildTagModel(), buildTagModel())
        val response = buildTagGridResponseDto(models, models.size.toLong())

        whenever(findTagsUseCase.find(dto)).thenReturn(response)

        tagController.find(request).let {
            assertThat(it).isNotNull()
        }

        verify(findTagsUseCase, times(1)).find(dto)
    }

    @Test
    fun `should successfully delete tag`() {
        val uid = Randomizer.generateRandomUid()
        val model = buildTagModel()

        whenever(deleteTagUseCase.delete(uid)).thenReturn(model)

        tagController.delete(uid).let {
            assertThat(it.uid).isNotBlank()
            assertThat(it.name).isEqualTo(model.name)
        }

        verify(deleteTagUseCase, times(1)).delete(uid)
    }
}