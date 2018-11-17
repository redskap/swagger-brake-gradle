package io.redskap.swagger.brake.gradle

import org.gradle.api.GradleException
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
        def checkTask = project.tasks.findByName("check")
        if (checkTask != null) {
            checkTask.dependsOn(checkBreakingChangesTask)
        } else {
            throw new GradleException("Plugin cannot be applied when no check task is present")
        }
    }
}
