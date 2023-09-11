package com.aoodax.platform.configuration.application.tag

import com.aoodax.platform.configuration.application.AbstractIntegrationTest
import com.aoodax.platform.contract.input.exception.AlreadyExistsException
import com.aoodax.platform.contract.input.exception.NotFoundException
import com.aoodax.platform.contract.input.tag.DeleteTagUseCase
import com.aoodax.platform.entrypoint.rest.api.controller.tag.TagController
import com.aoodax.platform.helper.integration.tag.TagIntegrationTestHelper
import com.aoodax.platform.helper.unit.contract.dto.CreateTagDtoUnitTestHelper
import com.aoodax.platform.helper.unit.entrypoint.rest.dto.common.request.PageableRequestUnitTestHelper.Companion.buildPageableRequest
import com.aoodax.platform.helper.unit.entrypoint.rest.dto.tag.request.TagRequestUnitTestHelper.Companion.buildCreateTagRequest
import com.aoodax.platform.helper.unit.entrypoint.rest.dto.tag.request.TagRequestUnitTestHelper.Companion.buildUpdateTagRequest
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class TagControllerIntegrationTest : AbstractIntegrationTest() {

    @Autowired
    private lateinit var controller: TagController

    @Autowired
    private lateinit var tagIntegrationTestHelper: TagIntegrationTestHelper

    @Autowired
    private lateinit var deleteTagUseCase: DeleteTagUseCase

    @Test
    fun `create tag`() {
        val request = buildCreateTagRequest()

        controller.create(request).let {
            assertThat(it.uid).isNotEmpty
            assertThat(it.name).isEqualTo(request.name)
        }
    }

    @Test
    fun `create tag when name already exists`() {
        val request = buildCreateTagRequest()

        tagIntegrationTestHelper.persistTag(
                dto = CreateTagDtoUnitTestHelper.buildCreateTagDto(
                        name = request.name
                )
        )

        Assertions.assertThatThrownBy {
            controller.create(request)
        }.isExactlyInstanceOf(AlreadyExistsException::class.java)
    }

    @Test
    fun `create tag with name that already created and deleted`() {
        val request = buildCreateTagRequest()
        val created = tagIntegrationTestHelper.persistTag(
                dto = CreateTagDtoUnitTestHelper.buildCreateTagDto(
                        name = request.name
                )
        )
        deleteTagUseCase.delete(created.uid)

        controller.create(request).let {
            assertThat(it.uid).isNotEmpty
            assertThat(it.name).isEqualTo(request.name)
        }
    }

    @Test
    fun `update tag`() {
        val tag = tagIntegrationTestHelper.persistTag()
        val request = buildUpdateTagRequest()

        controller.update(
                tag.uid,
                request
        ).let {
            assertThat(it.uid).isEqualTo(tag.uid)
            assertThat(it.name).isEqualTo(request.name)
        }
    }

    @Test
    fun `update tag when already exists for name`() {
        val tag = tagIntegrationTestHelper.persistTag()
        val request = buildUpdateTagRequest()

        tagIntegrationTestHelper.persistTag(
                dto = CreateTagDtoUnitTestHelper.buildCreateTagDto(
                        name = request.name
                )
        )

        Assertions.assertThatThrownBy {
            controller.update(tag.uid, request)
        }.isExactlyInstanceOf(AlreadyExistsException::class.java)
    }

    @Test
    fun `update tag with name that already created and deleted`() {
        val request = buildUpdateTagRequest()
        val tag = tagIntegrationTestHelper.persistTag(
                dto = CreateTagDtoUnitTestHelper.buildCreateTagDto(
                        name = request.name
                )
        )
        deleteTagUseCase.delete(tag.uid)
        val newTag = tagIntegrationTestHelper.persistTag(
                dto = CreateTagDtoUnitTestHelper.buildCreateTagDto(
                        name = request.name
                )
        )

        controller.update(newTag.uid, request).let {
            assertThat(it.uid).isNotEmpty
            assertThat(it.name).isEqualTo(request.name)
        }
    }

    @Test
    fun `update tag with same name that already exists for same tag`() {
        val request = buildUpdateTagRequest()
        val tag = tagIntegrationTestHelper.persistTag(
                dto = CreateTagDtoUnitTestHelper.buildCreateTagDto(
                        name = request.name
                )
        )

        controller.update(tag.uid, request).let {
            assertThat(it.uid).isNotEmpty
            assertThat(it.name).isEqualTo(request.name)
        }
    }

    @Test
    fun `getByUid tag`() {
        val tag = tagIntegrationTestHelper.persistTag()

        controller.getByUid(tag.uid).let {
            assertThat(it.uid).isEqualTo(tag.uid)
            assertThat(it.name).isEqualTo(tag.name)
        }
    }

    @Test
    fun `find tags`() {
        controller.find(buildPageableRequest(page = 1, size = 1000)).let { grid ->
            grid.items.forEach { controller.delete(it.uid) }
        }
        val tags = listOf(
                tagIntegrationTestHelper.persistTag(),
                tagIntegrationTestHelper.persistTag(),
                tagIntegrationTestHelper.persistTag(),
                tagIntegrationTestHelper.persistTag(),
                tagIntegrationTestHelper.persistTag(),
                tagIntegrationTestHelper.persistTag(),
                tagIntegrationTestHelper.persistTag(),
                tagIntegrationTestHelper.persistTag()
        )

        //unexpected tags
        deleteTagUseCase.delete(tags[1].uid)
        deleteTagUseCase.delete(tags[4].uid)

        controller.find(buildPageableRequest(page = 3, size = 2))
                .let {
                    assertThat(it.items[0].uid).isEqualTo(tags[2].uid)
                    assertThat(it.items[0].name).isEqualTo(tags[2].name)
                    assertThat(it.items[1].uid).isEqualTo(tags[0].uid)
                    assertThat(it.items[1].name).isEqualTo(tags[0].name)
                    assertThat(it.total).isGreaterThanOrEqualTo(6)
                }
    }

    @Test
    fun `delete tag`() {
        val tag = tagIntegrationTestHelper.persistTag()
        controller.delete(tag.uid)

        assertThatThrownBy {
            controller.getByUid(tag.uid)
        }.isExactlyInstanceOf(NotFoundException::class.java)
    }

    @Test
    fun `delete tag 2`() {
        val tags = listOf(
                tagIntegrationTestHelper.persistTag(),
                tagIntegrationTestHelper.persistTag(),
                tagIntegrationTestHelper.persistTag(),
        )
        val tagsUids = tags.map { it.uid }
        controller.delete(tags[0].uid)

        controller.find(buildPageableRequest(page = 1, size = 5000)).items.map { it.uid }.let {
            assertThat(it.containsAll(tagsUids))
        }
    }
}
