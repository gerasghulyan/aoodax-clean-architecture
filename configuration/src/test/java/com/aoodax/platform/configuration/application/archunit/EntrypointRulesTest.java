package com.aoodax.platform.configuration.application.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.disqo.adm.campaign.management")
public class EntrypointRulesTest {

    @ArchTest
    static final ArchRule classes_in_dto_should_have_name_ending_dto =
            classes()
                    .that()
                    .resideInAPackage("..entrypoint.*.dto..")
                    .and()
                    .areNotNestedClasses()
                    .should()
                    .haveSimpleNameEndingWith("Dto")
                    .allowEmptyShould(true);

    @ArchTest
    static final ArchRule classes_matching_dto_should_be_in_dto =
            classes()
                    .that()
                    .haveSimpleNameEndingWith("Dto")
                    .should()
                    .resideInAPackage("..entrypoint.*.dto..")
                    .orShould()
                    .resideInAPackage("..entrypoint.dto..")
                    .allowEmptyShould(true);

    @ArchTest
    static final ArchRule classes_in_listener_should_have_name_ending_listener =
            classes()
                    .that()
                    .resideInAPackage("..entrypoint.messaging.listener..")
                    .should()
                    .haveSimpleNameEndingWith("Listener")
                    .allowEmptyShould(true);

    @ArchTest
    static final ArchRule classes_matching_listener_should_be_in_listener =
            classes()
                    .that()
                    .haveSimpleNameEndingWith("Listener")
                    .should()
                    .resideInAPackage("..entrypoint.messaging.listener..")
                    .allowEmptyShould(true);

    @ArchTest
    static final ArchRule classes_in_rest_controller_should_have_name_ending_controller =
            classes()
                    .that()
                    .resideInAPackage("..entrypoint.rest.controller..")
                    .should()
                    .haveSimpleNameEndingWith("Controller")
                    .allowEmptyShould(true);

    @ArchTest
    static final ArchRule classes_matching_controller_should_be_in_rest_controller =
            classes()
                    .that()
                    .haveSimpleNameEndingWith("Controller")
                    .should()
                    .resideInAPackage("..entrypoint.rest.controller..")
                    .allowEmptyShould(true);

    @ArchTest
    static final ArchRule classes_in_entrypoint_controller_should_be_annotated_with_rest_controller =
            classes()
                    .that()
                    .resideInAPackage("..entrypoint.rest.controller..")
                    .and()
                    .haveSimpleNameEndingWith("Controller")
                    .should()
                    .beAnnotatedWith(RestController.class)
                    .allowEmptyShould(true);
    @ArchTest
    static final ArchRule classes_in_handler_should_have_name_ending_handler =
            classes()
                    .that()
                    .resideInAPackage("..handler..")
                    .should()
                    .haveNameMatching(".*Handler*.*")
                    .allowEmptyShould(true);
    @ArchTest
    static final ArchRule classes_matching_handler_should_be_in_handler =
            classes()
                    .that()
                    .haveSimpleNameEndingWith("Handler")
                    .should()
                    .resideInAPackage("..handler..")
                    .allowEmptyShould(true);
    @ArchTest
    static ArchRule classes_with_controller_annotation_should_be_in_entrypoint_controller =
            classes()
                    .that()
                    .areAnnotatedWith(RestController.class)
                    .should()
                    .resideInAPackage("..entrypoint.rest.controller..")
                    .andShould()
                    .haveSimpleNameEndingWith("Controller")
                    .allowEmptyShould(true);
}
