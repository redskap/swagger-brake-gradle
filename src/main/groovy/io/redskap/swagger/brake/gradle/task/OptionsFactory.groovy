package io.redskap.swagger.brake.gradle.task

import com.google.common.collect.ImmutableSet
import io.redskap.swagger.brake.runner.Options
import io.redskap.swagger.brake.runner.OutputFormat
import org.apache.commons.collections4.CollectionUtils
import org.apache.commons.lang3.StringUtils

import java.util.stream.Collectors

import static java.util.stream.Collectors.toSet

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
        def formats = parameter.outputFormats
        if (CollectionUtils.isEmpty(formats)) {
            return Collections.emptySet()
        }
        return ImmutableSet.copyOf(formats.collect { OutputFormat.valueOf(it.toUpperCase()) })
    }
}
