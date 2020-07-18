package io.redskap.swagger.brake.gradle

import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class SwaggerBrakePluginFunctionalTest extends Specification {
    public static final String GRADLE_VERSION = "6.5.1"
    public static final String TASK_NAME = "checkBreakingChanges"
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

            group = 'io.redskap'
            version = '2.0.0-SNAPSHOT' 

            swaggerBrake {
                mavenRepoUrl = "http://localhost:8081/artifactory/libs-release-local"
                mavenSnapshotRepoUrl = "http://localhost:8081/artifactory/libs-snapshot-local"
                newApi = "${testProjectDir.root.toString().replace('\\', '/')}/resources/main/swagger.yaml"
                testModeEnabled = true
            }
        """

        when:
        def result = createGradleRunner().build()

        then:
        result.task(":checkBreakingChanges").outcome == SUCCESS
    }

    def "checkBreakingChanges task works with single output format"() {
        given:
        settingsFile << """
            rootProject.name = 'swagger-brake-gradle-func-test'
        """

        buildFile << """
            plugins {
                id 'java'
                id 'io.redskap.swagger-brake'
            }

            group = 'io.redskap'
            version = '2.0.0-SNAPSHOT' 

            swaggerBrake {
                mavenRepoUrl = "http://localhost:8081/artifactory/libs-release-local"
                mavenSnapshotRepoUrl = "http://localhost:8081/artifactory/libs-snapshot-local"
                newApi = "${testProjectDir.root.toString().replace('\\', '/')}/resources/main/swagger.yaml"
                outputFormats = ["HTML"]
                testModeEnabled = true
            }
        """

        when:
        def result = createGradleRunner().build()

        then:
        result.task(":checkBreakingChanges").outcome == SUCCESS
    }

    def "checkBreakingChanges task works with multi output format"() {
        given:
        settingsFile << """
            rootProject.name = 'swagger-brake-gradle-func-test'
        """

        buildFile << """
            plugins {
                id 'java'
                id 'io.redskap.swagger-brake'
            }

            group = 'io.redskap'
            version = '2.0.0-SNAPSHOT' 

            swaggerBrake {
                mavenRepoUrl = "http://localhost:8081/artifactory/libs-release-local"
                mavenSnapshotRepoUrl = "http://localhost:8081/artifactory/libs-snapshot-local"
                newApi = "${testProjectDir.root.toString().replace('\\', '/')}/resources/main/swagger.yaml"
                outputFormats = ["HTML", "JSON"]
                testModeEnabled = true
            }
        """

        when:
        def result = createGradleRunner().build()

        then:
        result.task(":checkBreakingChanges").outcome == SUCCESS
    }

    private GradleRunner createGradleRunner() {
        GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withDebug(true)
                .withPluginClasspath()
                .withArguments(TASK_NAME)
                .withGradleVersion(GRADLE_VERSION)
    }
}
