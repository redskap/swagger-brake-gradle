package io.redskap.swagger.brake.gradle

import org.apache.commons.lang3.StringUtils
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.provider.Property

class SwaggerBrakeExtension {
    private static final Logger logger = Logging.getLogger(SwaggerBrakeExtension)

    Property<Object> newApi
    Property<Object> mavenRepoUrl
    Property<Object> groupId
    Property<Object> artifactId
    Property<Object> outputFilePath
    Property<Object> outputFormat

    SwaggerBrakeExtension(Project project) {
        this.newApi = project.getObjects().property(Object.class)
        this.mavenRepoUrl = project.getObjects().property(Object.class)
        this.groupId = project.getObjects().property(Object.class)
        this.artifactId = project.getObjects().property(Object.class)
        this.outputFilePath = project.getObjects().property(Object.class)
        this.outputFormat = project.getObjects().property(Object.class)
        applyDefaults(project)
    }

    private void applyDefaults(Project project) {
        applyDefaultArtifactId(project)
        applyDefaultGroupId(project)
        applyDefaultOutputFilePath(project)
        applyDefaultOutputFormat()
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
