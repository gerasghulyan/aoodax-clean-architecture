package com.aoodax.platform.configuration.application

import com.aoodax.jvm.common.testing.mongodb.AbstractMongoDbAwareIntegrationTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest(classes = [AoodaxPlatformApplication::class])
@TestPropertySource(value = ["classpath:application.properties"])
abstract class AbstractIntegrationTest : AbstractMongoDbAwareIntegrationTest()