package io.redskap.swagger.brake.gradle

import org.gradle.api.Project
import org.gradle.api.provider.Property

class SwaggerBrakeExtension {
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

        this.artifactId.set(project.name.toString())
        this.groupId.set(project.group.toString())
        this.outputFilePath.set("${project.buildDir}/swagger-brake/".toString())
        this.outputFormat.set('HTML')
    }
}
