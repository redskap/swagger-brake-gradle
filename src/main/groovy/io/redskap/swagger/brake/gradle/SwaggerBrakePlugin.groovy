package io.redskap.swagger.brake.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class SwaggerBrakePlugin implements Plugin<Project>  {
    @Override
    void apply(Project project) {
        def extension = project.extensions.create("swaggerBrake", SwaggerBrakeExtension.class, project)
        def checkBreakingChangesTask = project.tasks.create("checkBreakingChanges", CheckBreakingChangesTask) {
            mavenRepoUrl = extension.mavenRepoUrl
            newApi = extension.newApi
            artifactId = extension.artifactId
            groupId = extension.groupId
            outputFilePath = extension.outputFilePath
            outputFormat = extension.outputFormat
        }
        project.tasks.getByName("check").dependsOn(checkBreakingChangesTask)
    }
}
