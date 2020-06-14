package io.redskap.swagger.brake.gradle.task

import com.google.common.collect.ImmutableSet
import io.redskap.swagger.brake.runner.Options
import io.redskap.swagger.brake.runner.OutputFormat
import org.apache.commons.lang3.StringUtils

class OptionsFactory {
    static Options create(CheckBreakingChangesTaskParameter parameter) {
        Options options = new Options()
        options.setNewApiPath(parameter.newApi)
        options.setOldApiPath(parameter.oldApi)
        options.setMavenRepoUrl(parameter.mavenRepoUrl)
        options.setMavenSnapshotRepoUrl(parameter.mavenSnapshotRepoUrl)
        options.setArtifactId(parameter.artifactId)
        options.setGroupId(parameter.groupId)
        options.setCurrentArtifactVersion(parameter.currentVersion)
        options.setOutputFilePath(parameter.outputFilePath)
        options.setOutputFormats(resolveOutputFormat(parameter))
        options.setMavenRepoUsername(parameter.mavenRepoUsername)
        options.setMavenRepoPassword(parameter.mavenRepoPassword)
        options.setDeprecatedApiDeletionAllowed(parameter.deprecatedApiDeletionAllowed)
        options.setBetaApiExtensionName(StringUtils.defaultIfBlank(parameter.betaApiExtensionName, null))
        options.setApiFilename(StringUtils.defaultIfBlank(parameter.apiFilename, null))
        return options
    }

    private static Set<OutputFormat> resolveOutputFormat(CheckBreakingChangesTaskParameter parameter) {
        def format = parameter.outputFormat
        if (format == null) {
            return Collections.emptySet();
        }
        return ImmutableSet.of(OutputFormat.valueOf(format.toUpperCase()))
    }
}
