package io.redskap.swagger.brake.gradle.task

import io.redskap.swagger.brake.gradle.task.starter.StarterFactory
import io.redskap.swagger.brake.runner.OptionsValidator
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class CheckBreakingChangesTask extends DefaultTask {
    // Using Object everywhere because of https://github.com/gradle/gradle/pull/6536
    @Input
    final Property<Object> newApi = getProject().getObjects().property(Object)
    @Input
    final Property<Object> oldApi = getProject().getObjects().property(Object)
    @Input
    final Property<Object> mavenRepoUrl = getProject().getObjects().property(Object)
    @Input
    final Property<Object> mavenSnapshotRepoUrl = getProject().getObjects().property(Object)
    @Input
    final Property<Object> artifactId = getProject().getObjects().property(Object)
    @Input
    final Property<Object> groupId = getProject().getObjects().property(Object)
    @Input
    final Property<Object> currentVersion = getProject().getObjects().property(Object)
    @Input
    final Property<Object> outputFilePath = getProject().getObjects().property(Object)
    @Input
    final Property<Object> outputFormat = getProject().getObjects().property(Object)
    @Input
    final Property<Object> mavenRepoUsername = getProject().getObjects().property(Object)
    @Input
    final Property<Object> mavenRepoPassword = getProject().getObjects().property(Object)
    @Input
    final Property<Boolean> deprecatedApiDeletionAllowed = getProject().getObjects().property(Boolean)
    @Input
    final Property<Object> betaApiExtensionName = getProject().getObjects().property(Object)
    @Input
    final Property<Object> apiFilename = getProject().getObjects().property(Object)

    @Input
    final Property<Boolean> testModeEnabled = getProject().getObjects().property(Boolean)

    private final OptionsValidator optionsValidator = new OptionsValidator()

    @TaskAction
    void performCheck() {
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.newApi = newApi.get().toString()
        parameter.oldApi = oldApi.get().toString()
        parameter.mavenRepoUrl = mavenRepoUrl.get().toString()
        parameter.mavenSnapshotRepoUrl = mavenSnapshotRepoUrl.get().toString()
        parameter.artifactId = artifactId.get().toString()
        parameter.groupId = groupId.get().toString()
        parameter.currentVersion = currentVersion.get().toString()
        parameter.outputFilePath = outputFilePath.get().toString()
        parameter.outputFormat = outputFormat.get().toString()
        parameter.mavenRepoUsername = mavenRepoUsername.get().toString()
        parameter.mavenRepoPassword = mavenRepoPassword.get().toString()
        parameter.deprecatedApiDeletionAllowed = deprecatedApiDeletionAllowed.get()
        parameter.betaApiExtensionName = betaApiExtensionName.get().toString()
        parameter.apiFilename = apiFilename.get().toString()

        logger.info("The following parameters are set for the task {}", parameter)
        def options = OptionsFactory.create(parameter)
        optionsValidator.validate(options)
        createExecutor().execute(options)
    }

    private CheckBreakingChangesTaskExecutor createExecutor() {
        def factory = new StarterFactory(testModeEnabled.get())
        return new CheckBreakingChangesTaskExecutor(factory)
    }
}
