package io.redskap.swagger.brake.gradle

import org.gradle.api.GradleException
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class SwaggerBrakePluginTest extends Specification {
    def "checkBreakingChanges is added when plugin is applied along with java plugin"() {
        given:
        def project = ProjectBuilder.builder().withName("test-project").build()

        when:
        project.pluginManager.apply('java')
        project.pluginManager.apply(SwaggerBrakePlugin)

        then:
        project.tasks.findByName("checkBreakingChanges") != null
    }

    def "Plugin fails when check task is missing"() {
        given:
        def project = ProjectBuilder.builder().withName("test-project").build()

        when:
        project.pluginManager.apply(SwaggerBrakePlugin)

        then:
        GradleException e = thrown()
        e.getCause().message.contains('applied')
        e.getCause().message.contains('check')
    }
}
