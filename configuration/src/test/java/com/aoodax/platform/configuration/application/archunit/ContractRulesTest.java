package com.aoodax.platform.configuration.application.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.disqo.adm.campaign.management")
public class ContractRulesTest {

    @ArchTest
    public static final ArchRule classes_matching_input_should_be_in_contract_input =
            classes()
                    .that()
                    .haveNameMatching("..InputBoundary..")
                    .should()
                    .resideInAPackage("..contract.input..")
                    .allowEmptyShould(true);

    @ArchTest
    public static final ArchRule classes_matching_output_should_be_in_contract_output =
            classes()
                    .that()
                    .haveNameMatching("..OutputBoundary..")
                    .should()
                    .resideInAPackage("..contract.output..")
                    .allowEmptyShould(true);

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
                    .allowEmptyShould(true);

    @ArchTest
    static final ArchRule classes_in_params_should_hav_name_ending_params =
            classes()
                    .that()
                    .resideInAPackage("..contract.params..")
                    .and()
                    .areNotInterfaces()
                    .and()
                    .areNotNestedClasses()
                    .should()
                    .haveNameMatching(".*Params.*")
                    .allowEmptyShould(true);
}
