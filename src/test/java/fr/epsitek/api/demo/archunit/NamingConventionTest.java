package fr.epsitek.api.demo.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "fr.epsitek.api.demo")
public class NamingConventionTest {

    @ArchTest
    static ArchRule services_should_end_with_service =
            classes()
                    .that().resideInAPackage("..services..")
                    .should().haveSimpleNameEndingWith("Service");

    @ArchTest
    static ArchRule classes_named_service_should_be_in_a_service_package =
            classes()
                    .that().haveSimpleNameContaining("Service")
                    .should().resideInAPackage("..services..");

    @ArchTest
    static ArchRule controllers_should_end_with_controller =
            classes()
                    .that().resideInAPackage("..controller..")
                    .should().haveSimpleNameEndingWith("Controller");

    @ArchTest
    static ArchRule classes_named_controller_should_be_in_a_controller_package =
            classes()
                    .that().haveSimpleNameContaining("Controller")
                    .should().resideInAPackage("..controller..");
}
