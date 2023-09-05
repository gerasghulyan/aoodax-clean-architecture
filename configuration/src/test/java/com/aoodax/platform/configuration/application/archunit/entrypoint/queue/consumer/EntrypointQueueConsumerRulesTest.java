package com.aoodax.platform.configuration.application.archunit.entrypoint.queue.consumer;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.aoodax.platform")
public class EntrypointQueueConsumerRulesTest {

    @ArchTest
    static final ArchRule classes_in_listener_should_have_name_ending_consumer =
            classes()
                    .that()
                    .resideInAPackage(".entrypoint.queue.consumer..")
                    .should()
                    .haveSimpleNameEndingWith("Consumer")
                    .allowEmptyShould(true);

    @ArchTest
    static final ArchRule classes_matching_listener_should_be_in_consumer =
            classes()
                    .that()
                    .haveSimpleNameEndingWith("Consumer")
                    .should()
                    .resideInAPackage(".entrypoint.queue.consumer..")
                    .allowEmptyShould(true);

}
