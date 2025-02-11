package fr.epsitek.api.demo.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "fr.epsitek.api.demo")
public class DomainPackageRuleTest {

    @ArchTest
    static final ArchRule domain_package_should_not_depend_on_application_package =
            noClasses().that().resideInAPackage("..domain..")
                    .should().accessClassesThat().resideInAPackage("..application..");

    @ArchTest
    static final ArchRule domain_package_should_not_depend_on_infrastructure_package =
            noClasses().that().resideInAPackage("..domain..")
                    .should().accessClassesThat().resideInAPackage("..infrastructure..");

}
