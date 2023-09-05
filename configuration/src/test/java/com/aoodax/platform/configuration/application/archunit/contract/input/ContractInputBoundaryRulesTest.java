package com.aoodax.platform.configuration.application.archunit.contract.input;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.aoodax.platform.contract.input")
public class ContractInputBoundaryRulesTest {

    @ArchTest
    public static final ArchRule classes_matching_input_should_be_in_contract_input =
            classes()
                    .that()
                    .haveNameMatching("..UseCase..")
                    .should()
                    .resideInAPackage("..contract.input..")
                    .allowEmptyShould(true);
}
