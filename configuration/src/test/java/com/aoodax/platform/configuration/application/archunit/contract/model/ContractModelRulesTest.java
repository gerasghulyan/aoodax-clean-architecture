package com.aoodax.platform.configuration.application.archunit.contract.model;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.aoodax.platform.contract.model")
public class ContractModelRulesTest {

    @ArchTest
    static final ArchRule classes_in_model_should_have_name_ending_model =
            classes()
                    .that()
                    .resideInAPackage("..contract.model..")
                    .and()
                    .areNotInterfaces()
                    .and()
                    .areNotNestedClasses()
                    .should()
                    .haveNameMatching(".*Model.*")
                    .orShould()
                    .haveNameMatching(".*Dto.*")
                    .allowEmptyShould(true);

    @ArchTest
    static final ArchRule classes_in_params_should_hav_name_ending_params =
            classes()
                    .that()
                    .resideInAPackage("..contract.model.params..")
                    .and()
                    .areNotInterfaces()
                    .and()
                    .areNotNestedClasses()
                    .should()
                    .haveNameMatching(".*Params.*")
                    .allowEmptyShould(true);
}
