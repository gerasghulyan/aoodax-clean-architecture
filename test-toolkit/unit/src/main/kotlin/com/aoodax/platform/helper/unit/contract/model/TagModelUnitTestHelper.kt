package com.aoodax.platform.helper.unit.contract.model

import com.aoodax.jvm.common.test.toolkit.Randomizer.Companion.generateRandomString
import com.aoodax.jvm.common.test.toolkit.Randomizer.Companion.generateRandomUid
import com.aoodax.platform.contract.model.tag.TagModel

class TagModelUnitTestHelper {

    companion object {

        fun buildCreateTagModel(
            name: String? = generateRandomString()
        ): TagModel =
            TagModel(
                name
            )

        fun buildTagModel(
            uid: String? = generateRandomUid(),
            name: String? = generateRandomString()
        ): TagModel = TagModel.builder()
            .uid(uid)
            .name(name)
            .build()

        fun buildTagModelForCreation(
            name: String? = generateRandomString()
        ): TagModel = TagModel.builderForCreation()
            .name(name)
            .build()
    }
}