package com.aoodax.platform.configuration.application.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.disqo.adm.campaign.management")
public class UseCaseRulesTest {

    @ArchTest
    public static final ArchRule classes_in_usecase_should_have_name_ending_usecase_impl =
            classes()
                    .that()
                    .resideInAPackage("..campaign.management.usecase..")
                    .and()
                    .areNotNestedClasses()
                    .should()
                    .haveSimpleNameEndingWith("UseCase")
                    .allowEmptyShould(true);
}
