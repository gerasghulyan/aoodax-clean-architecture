package com.aoodax.platform.configuration.application.archunit.entrypoint.rest.dto;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = " com.aoodax.platform.entrypoint.rest.dto")
public class EntryPointRestDtoRulesTest {

    @ArchTest
    static final ArchRule classes_in_dto_should_have_name_ending_dto =
            classes()
                    .that()
                    .resideInAPackage(".entrypoint.*.dto..")
                    .and()
                    .areNotNestedClasses()
                    .should()
                    .haveSimpleNameEndingWith("Request")
                    .orShould()
                    .haveSimpleNameEndingWith("Response")
                    .allowEmptyShould(true);

    @ArchTest
    static final ArchRule classes_matching_dto_should_be_in_dto =
            classes()
                    .that()
                    .haveSimpleNameEndingWith("Request")
                    .or()
                    .haveSimpleNameEndingWith("Response")
                    .should()
                    .resideInAPackage(".entrypoint.*.dto..")
                    .orShould()
                    .resideInAPackage(".entrypoint.dto..")
                    .allowEmptyShould(true);
}
