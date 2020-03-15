package io.redskap.swagger.brake.gradle

import io.redskap.swagger.brake.gradle.task.CheckBreakingChangesTask
import io.redskap.swagger.brake.gradle.task.CheckBreakingChangesTaskExecutor
import io.redskap.swagger.brake.gradle.task.CheckBreakingChangesTaskParameterValidator
import io.redskap.swagger.brake.gradle.task.starter.StarterFactory
import io.redskap.swagger.brake.gradle.task.starter.SwaggerBrakeStarter
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
            mavenRepoUsername = extension.mavenRepoUsername
            mavenRepoPassword = extension.mavenRepoPassword
            deprecatedApiDeletionAllowed = extension.deprecatedApiDeletionAllowed
            betaApiExtensionName = extension.betaApiExtensionName
            apiFilename = extension.apiFilename
            testModeEnabled = extension.testModeEnabled
        }
        def checkTask = project.tasks.findByName("check")
        if (checkTask != null) {
            checkTask.dependsOn(checkBreakingChangesTask)
        } else {
            throw new GradleException("Plugin cannot be applied when no check task is present")
        }
    }
}
