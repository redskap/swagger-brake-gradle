package io.redskap.swagger.brake.gradle.task

import io.redskap.swagger.brake.gradle.task.starter.StarterFactory
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class CheckBreakingChangesTask extends DefaultTask {
    // Using Object everywhere because of https://github.com/gradle/gradle/pull/6536
    @Input
    final Property<Object> newApi = getProject().getObjects().property(Object.class)
    @Input
    final Property<Object> mavenRepoUrl = getProject().getObjects().property(Object.class)
    @Input
    final Property<Object> groupId = getProject().getObjects().property(Object.class)
    @Input
    final Property<Object> artifactId = getProject().getObjects().property(Object.class)
    @Input
    final Property<Object> outputFilePath = getProject().getObjects().property(Object.class)
    @Input
    final Property<Object> outputFormat = getProject().getObjects().property(Object.class)
    @Input
    final Property<Object> mavenRepoUsername = getProject().getObjects().property(Object.class)
    @Input
    final Property<Object> mavenRepoPassword = getProject().getObjects().property(Object.class)


    @Input
    final Property<Boolean> testModeEnabled = getProject().getObjects().property(Boolean.class)

    @TaskAction
    void performCheck() {
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.newApi = newApi.get().toString()
        parameter.mavenRepoUrl = mavenRepoUrl.get().toString()
        parameter.groupId = groupId.get().toString()
        parameter.artifactId = artifactId.get().toString()
        parameter.outputFilePath = outputFilePath.get().toString()
        parameter.outputFormat = outputFormat.get().toString()
        parameter.mavenRepoUsername = mavenRepoUsername.get().toString()
        parameter.mavenRepoPassword = mavenRepoPassword.get().toString()

        createExecutor().execute(parameter)
    }

    private CheckBreakingChangesTaskExecutor createExecutor() {
        def factory = new StarterFactory(testModeEnabled.get())
        def validator = new CheckBreakingChangesTaskParameterValidator()
        return new CheckBreakingChangesTaskExecutor(validator, factory)
    }
}
