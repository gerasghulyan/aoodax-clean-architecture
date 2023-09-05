package com.aoodax.platform.configuration.application.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.aoodax.platform")
public class LayerDependencyRulesTest {

    @ArchTest
    public static final ArchRule usecase_should_not_depend_on_entrypoint = noClasses().that()
            .resideInAPackage("..usecase..")
            .should()
            .dependOnClassesThat()
            .resideInAPackage("..entrypoint..")
            .allowEmptyShould(true);

    @ArchTest
    public static final ArchRule usecase_should_not_depend_on_infrastructure = noClasses().that()
            .resideInAPackage(".usecase..")
            .should()
            .dependOnClassesThat()
            .resideInAPackage("..infrastructure..")
            .allowEmptyShould(true);

    @ArchTest
    public static final ArchRule entrypoint_should_not_depend_on_infrastructure = noClasses().that()
            .resideInAPackage("..entrypoint..")
            .should()
            .dependOnClassesThat()
            .resideInAPackage("..infrastructure..")
            .allowEmptyShould(true);
    @ArchTest
    public static final ArchRule entrypoint_should_not_depend_on_usecase = noClasses().that()
            .resideInAPackage(".entrypoint..")
            .should()
            .dependOnClassesThat()
            .resideInAPackage(".usecase..")
            .allowEmptyShould(true);

    @ArchTest
    public static final ArchRule contract_should_not_have_dependencies = noClasses().that()
            .resideInAPackage(".contract..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..entrypoint..", "..usecase..", "..infrastructure..")
            .allowEmptyShould(true);

    @ArchTest
    public static final ArchRule contract_input_boundary_should_not_have_dependencies = noClasses().that()
            .resideInAPackage(".contract.input..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..entrypoint..", "..usecase..", "..infrastructure..")
            .allowEmptyShould(true);

    @ArchTest
    public static final ArchRule contract_model_should_not_have_dependencies = noClasses().that()
            .resideInAPackage(".contract.model..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..entrypoint..", "..usecase..", "..infrastructure..")
            .allowEmptyShould(true);

    @ArchTest
    public static final ArchRule contract_output_boundary_should_not_have_dependencies = noClasses().that()
            .resideInAPackage(".contract.output..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..entrypoint..", "..usecase.")
            .allowEmptyShould(true);
}
