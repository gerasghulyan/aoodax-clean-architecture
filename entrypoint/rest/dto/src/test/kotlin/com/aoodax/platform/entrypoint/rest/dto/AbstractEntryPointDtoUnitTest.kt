package com.aoodax.platform.entrypoint.rest.dto

import com.aoodax.jvm.common.testing.unit.AbstractMockitoAwareUnitTest
import jakarta.validation.Validation
import jakarta.validation.Validator
import jakarta.validation.ValidatorFactory
import org.junit.jupiter.api.BeforeEach

abstract class AbstractEntryPointDtoUnitTest : AbstractMockitoAwareUnitTest() {

    lateinit var validator: Validator

    @BeforeEach
    fun setUp() {
        val factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }
}
