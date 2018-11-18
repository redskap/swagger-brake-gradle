package io.redskap.swagger.brake.gradle.task

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
        options.setOutputFormat(OutputFormat.valueOf(parameter.outputFormat))
        return options
    }
}
