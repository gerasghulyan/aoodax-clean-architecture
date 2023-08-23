package com.aoodax.platform.configuration.application.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.disqo.adm.campaign.management")
public class LayerDependencyRulesTest {

    @ArchTest
    public static final ArchRule usecase_should_not_depend_on_entrypoint = noClasses().that()
            .resideInAPackage("..campaign.management.usecase..")
            .should()
            .dependOnClassesThat()
            .resideInAPackage("..campaign.management.entrypoint..")
            .allowEmptyShould(true);

    @ArchTest
    public static final ArchRule usecase_should_not_depend_on_infrastructure = noClasses().that()
            .resideInAPackage("..campaign.management.usecase..")
            .should()
            .dependOnClassesThat()
            .resideInAPackage("..campaign.management.infrastructure..")
            .allowEmptyShould(true);

    @ArchTest
    public static final ArchRule entrypoint_should_not_depend_on_infrastructure = noClasses().that()
            .resideInAPackage("..campaign.management.entrypoint..")
            .should()
            .dependOnClassesThat()
            .resideInAPackage("..campaign.management.infrastructure..")
            .allowEmptyShould(true);
    @ArchTest
    public static final ArchRule entrypoint_should_not_depend_on_usecase = noClasses().that()
            .resideInAPackage("..campaign.management.entrypoint..")
            .should()
            .dependOnClassesThat()
            .resideInAPackage("..campaign.management.usecase..")
            .allowEmptyShould(true);

    @ArchTest
    public static final ArchRule contract_should_not_have_dependencies = noClasses().that()
            .resideInAPackage("..campaign.management.contract..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..campaign.management.entrypoint..", "..campaign.management.usecase..", "..campaign.management.infrastructure..")
            .allowEmptyShould(true);
}
