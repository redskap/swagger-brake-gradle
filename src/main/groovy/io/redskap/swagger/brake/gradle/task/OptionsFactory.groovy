package io.redskap.swagger.brake.gradle.task

import com.google.common.collect.ImmutableSet
import io.redskap.swagger.brake.runner.Options
import io.redskap.swagger.brake.runner.OutputFormat

class OptionsFactory {
    static Options create(CheckBreakingChangesTaskParameter parameter) {
        Options options = new Options()
        options.setNewApiPath(parameter.newApi)
        options.setMavenRepoUrl(parameter.mavenRepoUrl)
        options.setGroupId(parameter.groupId)
        options.setArtifactId(parameter.artifactId)
        options.setOutputFilePath(parameter.outputFilePath)
        options.setOutputFormats(resolveOutputFormat(parameter))
        options.setMavenRepoUsername(parameter.mavenRepoUsername)
        options.setMavenRepoPassword(parameter.mavenRepoPassword)
        return options
    }

    private static Set<OutputFormat> resolveOutputFormat(CheckBreakingChangesTaskParameter parameter) {
        return ImmutableSet.of(OutputFormat.valueOf(parameter.outputFormat.toUpperCase()))
    }
}
