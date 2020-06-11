package io.redskap.swagger.brake.gradle.task

import org.apache.commons.lang3.StringUtils

class CheckBreakingChangesTaskParameterValidator {
    void validate(CheckBreakingChangesTaskParameter parameter) {
        if (StringUtils.isBlank(parameter.mavenRepoUrl)
                && StringUtils.isBlank(parameter.oldApi) ) {
            throw new IllegalArgumentException("mavenRepoUrl or oldApi must be set")
        }
        if (StringUtils.isBlank(parameter.newApi)) {
            throw new IllegalArgumentException("newApi must be set")
        }
        if (StringUtils.isBlank(parameter.groupId)) {
            throw new IllegalArgumentException("groupId must be set")
        }
        if (StringUtils.isBlank(parameter.artifactId)) {
            throw new IllegalArgumentException("artifactId must be set")
        }
        if (StringUtils.isBlank(parameter.outputFilePath)) {
            throw new IllegalArgumentException("outputFilePath must be set")
        }
        if (StringUtils.isBlank(parameter.outputFormat)) {
            throw new IllegalArgumentException("outputFormat must be set")
        }
    }
}
