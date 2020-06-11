package io.redskap.swagger.brake.gradle.task

import org.apache.commons.lang3.StringUtils

import static org.apache.commons.lang3.StringUtils.isBlank
import static org.apache.commons.lang3.StringUtils.isNotBlank

class CheckBreakingChangesTaskParameterValidator {
    void validate(CheckBreakingChangesTaskParameter parameter) {
        if (isBlank(parameter.mavenRepoUrl) && isBlank(parameter.oldApi) ) {
            throw new IllegalArgumentException("mavenRepoUrl or oldApi must be set")
        }
        if (isNotBlank(parameter.mavenRepoUrl) && isNotBlank(parameter.oldApi)) {
            throw new IllegalArgumentException("mavenRepoUrl and oldApi cannot be set together")
        }
        if (isBlank(parameter.newApi)) {
            throw new IllegalArgumentException("newApi must be set")
        }
        if (isBlank(parameter.groupId)) {
            throw new IllegalArgumentException("groupId must be set")
        }
        if (isBlank(parameter.artifactId)) {
            throw new IllegalArgumentException("artifactId must be set")
        }
        if (isBlank(parameter.outputFilePath)) {
            throw new IllegalArgumentException("outputFilePath must be set")
        }
        if (isBlank(parameter.outputFormat)) {
            throw new IllegalArgumentException("outputFormat must be set")
        }
    }
}
