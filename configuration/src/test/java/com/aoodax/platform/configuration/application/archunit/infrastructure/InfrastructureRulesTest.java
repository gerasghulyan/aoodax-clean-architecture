package com.aoodax.platform.configuration.application.archunit.infrastructure;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.aoodax.platform")
public class InfrastructureRulesTest {

    @ArchTest
    public static final ArchRule classes_matching_repository_should_be_in_repository =
            classes()
                    .that()
                    .haveNameMatching("..Repository..")
                    .should()
                    .resideInAPackage("..infrastructure.db.repository..")
                    .allowEmptyShould(true);
    @ArchTest
    public static final ArchRule classes_in_adapter_should_have_name_ending_adapter =
            classes()
                    .that()
                    .resideInAPackage("..infrastructure.db.repository.adapter..")
                    .and()
                    .areNotNestedClasses()
                    .should()
                    .haveSimpleNameEndingWith("Adapter")
                    .allowEmptyShould(true);
    @ArchTest
    public static final ArchRule classes_in_repository_should_be_annotated_with_repository =
            classes()
                    .that()
                    .resideInAPackage("..infrastructure.db.repository..")
                    .and()
                    .areNotNestedClasses()
                    .and()
                    .haveSimpleNameEndingWith("Repository")
                    .should()
                    .beAnnotatedWith(Repository.class)
                    .allowEmptyShould(true);
    @ArchTest
    static final ArchRule classes_in_document_should_have_name_ending_document =
            classes()
                    .that()
                    .resideInAPackage("..document..")
                    .and()
                    .areNotNestedClasses()
                    .should()
                    .haveSimpleNameEndingWith("Document")
                    .orShould()
                    .haveSimpleNameEndingWith("Constraint")
                    .allowEmptyShould(true);
    @ArchTest
    static final ArchRule classes_matching_schema_should_be_in_schema =
            classes()
                    .that()
                    .haveSimpleNameEndingWith("Entity")
                    .should()
                    .resideInAPackage("..infrastructure.domain.entity..")
                    .allowEmptyShould(true);
    @ArchTest
    static ArchRule classes_with_repository_annotation_should_be_in_repository_impl =
            classes()
                    .that()
                    .areAnnotatedWith(Repository.class)
                    .should()
                    .resideInAPackage("..infrastructure.repository..")
                    .andShould()
                    .haveSimpleNameEndingWith("Repository")
                    .orShould()
                    .haveSimpleNameEndingWith("RepositoryImpl")
                    .allowEmptyShould(true);
}
