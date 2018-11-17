package io.redskap.swagger.brake.gradle

import io.redskap.swagger.brake.runner.Options
import io.redskap.swagger.brake.runner.OutputFormat
import io.redskap.swagger.brake.runner.Starter
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class CheckBreakingChangesTask extends DefaultTask {
    // Using Object everywhere because of https://github.com/gradle/gradle/pull/6536
    @Input
    Property<Object> newApi = getProject().getObjects().property(Object.class)
    @Input
    Property<Object> mavenRepoUrl = getProject().getObjects().property(Object.class)
    @Input
    Property<Object> groupId = getProject().getObjects().property(Object.class)
    @Input
    Property<Object> artifactId = getProject().getObjects().property(Object.class)
    @Input
    Property<Object> outputFilePath = getProject().getObjects().property(Object.class)
    @Input
    Property<Object> outputFormat = getProject().getObjects().property(Object.class)

    @TaskAction
    void performCheck() {
        Options options = new Options()
        options.setNewApiPath(newApi.get().toString())
        options.setMavenRepoUrl(mavenRepoUrl.get().toString())
        options.setGroupId(groupId.get().toString())
        options.setArtifactId(artifactId.get().toString())
        options.setOutputFilePath(outputFilePath.get().toString())
        options.setOutputFormat(OutputFormat.valueOf(outputFormat.get().toString()))
        Starter.start(options)
    }
}
