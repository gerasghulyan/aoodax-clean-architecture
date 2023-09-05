package com.aoodax.platform.configuration.application.archunit.contract.output;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.aoodax.platform.contract.output")
public class ContractOutputBoundaryRulesTest {

    @ArchTest
    public static final ArchRule classes_matching_output_should_be_in_contract_output =
            classes()
                    .that()
                    .haveNameMatching("..Repository..")
                    .should()
                    .resideInAPackage("..contract.output..")
                    .allowEmptyShould(true);
}
