package com.aoodax.platform.configuration.application.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

@AnalyzeClasses(packages = "com.disqo.adm.campaign.management")
public class UnitTestRulesTest {

    @ArchTest
    static ArchRule methods_annotated_with_test_should_finish_with_ok_or_exception =
            methods()
                    .that()
                    .areAnnotatedWith(Test.class)
                    .should()
                    .haveNameMatching(".*_ok")
                    .orShould()
                    .haveNameMatching(".*_exception")
                    .orShould()
                    .haveNameMatching("contextLoads")
                    .allowEmptyShould(true);
}
