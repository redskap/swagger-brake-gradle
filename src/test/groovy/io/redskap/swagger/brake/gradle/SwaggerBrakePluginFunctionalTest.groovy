package io.redskap.swagger.brake.gradle

import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class SwaggerBrakePluginFunctionalTest extends Specification {
    @Rule
    final TemporaryFolder testProjectDir = new TemporaryFolder()

    File buildFile
    File settingsFile

    def setup() {
        buildFile = testProjectDir.newFile('build.gradle')
        settingsFile = testProjectDir.newFile('settings.gradle')
    }

    def "checkBreakingChanges task works"() {
        given:
        settingsFile << """
            rootProject.name = 'swagger-brake-gradle-func-test'
        """

        buildFile << """
            plugins {
                id 'java'
                id 'io.redskap.swagger-brake'
            }

            swaggerBrake {
                groupId = 'io.redskap'
                mavenRepoUrl = "http://localhost:8081/artifactory/libs-snapshot-local"
                newApi = "${testProjectDir.root.toString().replace('\\', '/')}/resources/main/swagger.yaml"
                testModeEnabled = true
            }
        """

        when:
        def result = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withDebug(true)
                .withPluginClasspath()
                .withArguments("checkBreakingChanges")
                .withGradleVersion("4.8")
                .build()

        then:
        result.task(":checkBreakingChanges").outcome == SUCCESS
    }
}
