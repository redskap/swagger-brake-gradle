package io.redskap.swagger.brake.gradle

import org.apache.commons.lang3.StringUtils
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.provider.Property

class SwaggerBrakeExtension {
    private static final Logger logger = Logging.getLogger(SwaggerBrakeExtension)

    final Property<Object> newApi
    final Property<Object> oldApi
    final Property<Object> mavenRepoUrl
    final Property<Object> groupId
    final Property<Object> artifactId
    final Property<Object> outputFilePath
    final Property<Object> outputFormat
    final Property<Object> mavenRepoUsername
    final Property<Object> mavenRepoPassword
    final Property<Boolean> deprecatedApiDeletionAllowed
    final Property<Object> betaApiExtensionName
    final Property<Object> apiFilename

    final Property<Boolean> testModeEnabled

    SwaggerBrakeExtension(Project project) {
        this.newApi = project.getObjects().property(Object)
        this.oldApi = project.getObjects().property(Object)
        this.mavenRepoUrl = project.getObjects().property(Object)
        this.groupId = project.getObjects().property(Object)
        this.artifactId = project.getObjects().property(Object)
        this.outputFilePath = project.getObjects().property(Object)
        this.outputFormat = project.getObjects().property(Object)
        this.mavenRepoUsername = project.getObjects().property(Object)
        this.mavenRepoPassword = project.getObjects().property(Object)
        this.deprecatedApiDeletionAllowed = project.getObjects().property(Boolean)
        this.betaApiExtensionName = project.getObjects().property(Object)
        this.apiFilename = project.getObjects().property(Object)
        this.testModeEnabled = project.getObjects().property(Boolean)
        applyDefaults(project)
    }

    private void applyDefaults(Project project) {
        applyDefaultOldApi()
        applyDefaultMavenRepoUrl()
        applyDefaultArtifactId(project)
        applyDefaultGroupId(project)
        applyDefaultOutputFilePath(project)
        applyDefaultOutputFormat()
        applyMavenRepoAuth()
        applyDeprecatedApiHandling()
        applyDefaultBetaApiExtensionName()
        applyDefaultApiFilename()
        applyTestMode()
    }

    private applyDefaultMavenRepoUrl() {
        this.mavenRepoUrl.set("")
    }

    private applyDefaultOldApi() {
        this.oldApi.set("")
    }

    private applyDefaultBetaApiExtensionName() {
        this.betaApiExtensionName.set("")
    }

    private applyDefaultApiFilename() {
        this.apiFilename.set("")
    }

    private applyDeprecatedApiHandling() {
        this.deprecatedApiDeletionAllowed.set(true)
    }

    private applyTestMode() {
        this.testModeEnabled.set(false)
    }

    private applyMavenRepoAuth() {
        this.mavenRepoUsername.set("")
        this.mavenRepoPassword.set("")
    }

    private applyDefaultArtifactId(Project project) {
        def artifactId = project.name.toString()
        if (StringUtils.isBlank(artifactId)) {
            logger.warn("Default artifactId based on project name cannot be used for Swagger Brake. Consider setting it manually.")
        }
        this.artifactId.set(artifactId)
    }

    private applyDefaultGroupId(Project project) {
        def groupId = project.group.toString()
        if (StringUtils.isBlank(groupId)) {
            logger.warn("Default groupId based on project group cannot be used for Swagger Brake. Consider setting it manually.")
        }
        this.groupId.set(groupId)
    }

    private applyDefaultOutputFilePath(Project project) {
        def outputFilePath = "${project.buildDir}/swagger-brake/".toString()
        if (StringUtils.isBlank(outputFilePath)) {
            logger.warn("Default outputFilePath based on project's buildDir cannot be used for Swagger Brake. Consider setting it manually.")
        }
        this.outputFilePath.set(outputFilePath)
    }

    private applyDefaultOutputFormat() {
        this.outputFormat.set('HTML')
    }
}
