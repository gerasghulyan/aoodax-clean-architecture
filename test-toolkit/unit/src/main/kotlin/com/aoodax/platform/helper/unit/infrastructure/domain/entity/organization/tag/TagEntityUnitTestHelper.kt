package com.aoodax.platform.helper.unit.infrastructure.domain.entity.organization.tag

import com.aoodax.jvm.common.test.toolkit.Randomizer.Companion.generateRandomString
import com.aoodax.platform.infrastructure.domain.entity.organization.tag.TagEntity
import java.time.LocalDateTime

class TagEntityUnitTestHelper {

    companion object {

        fun buildTagEntity(
            uid: String? = generateRandomString(),
            createdAt: LocalDateTime? = LocalDateTime.now(),
            updatedAt: LocalDateTime? = LocalDateTime.now(),
            isDeleted: Boolean = false,
            version: Long? = 1,
            name: String? = generateRandomString(),
        ): TagEntity = TagEntity(
            uid,
            createdAt,
            updatedAt,
            isDeleted,
            version,
            name,
        )
    }
}