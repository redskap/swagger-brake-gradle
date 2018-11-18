package io.redskap.swagger.brake.gradle.task


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

    private final CheckBreakingChangesTaskExecutor executor = new CheckBreakingChangesTaskExecutor()

    @TaskAction
    void performCheck() {
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.newApi = newApi.get().toString()
        parameter.mavenRepoUrl = mavenRepoUrl.get().toString()
        parameter.groupId = groupId.get().toString()
        parameter.artifactId = artifactId.get().toString()
        parameter.outputFilePath = outputFilePath.get().toString()
        parameter.outputFormat = outputFormat.get().toString()

        executor.execute(parameter)
    }
}
