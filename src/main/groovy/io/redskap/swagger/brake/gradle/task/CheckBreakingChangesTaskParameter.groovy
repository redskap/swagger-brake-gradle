package io.redskap.swagger.brake.gradle.task

import io.redskap.swagger.brake.runner.OutputFormat
import org.apache.commons.collections4.CollectionUtils
import org.gradle.api.Project
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property

import static org.apache.commons.lang3.StringUtils.isBlank
import static org.apache.commons.lang3.StringUtils.isBlank
import static org.apache.commons.lang3.StringUtils.isBlank
import static org.apache.commons.lang3.StringUtils.isBlank
import static org.apache.commons.lang3.StringUtils.isBlank
import static org.apache.commons.lang3.StringUtils.isBlank
import static org.apache.commons.lang3.StringUtils.isBlank
import static org.apache.commons.lang3.StringUtils.isBlank

class CheckBreakingChangesTaskParameter {
    String newApi
    String oldApi
    String mavenRepoUrl
    String mavenSnapshotRepoUrl
    String artifactId
    String groupId
    String currentVersion
    String artifactPackaging
    String outputFilePath
    List<String> outputFormats
    String mavenRepoUsername
    String mavenRepoPassword
    Boolean deprecatedApiDeletionAllowed
    String betaApiExtensionName
    String apiFilename
    List<String> excludedPaths


    @Override
    public String toString() {
        return "CheckBreakingChangesTaskParameter{" +
                "newApi='" + newApi + '\'' +
                ", oldApi='" + oldApi + '\'' +
                ", mavenRepoUrl='" + mavenRepoUrl + '\'' +
                ", mavenSnapshotRepoUrl='" + mavenSnapshotRepoUrl + '\'' +
                ", artifactId='" + artifactId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", currentVersion='" + currentVersion + '\'' +
                ", artifactPackaging='" + artifactPackaging + '\'' +
                ", outputFilePath='" + outputFilePath + '\'' +
                ", outputFormats=" + outputFormats +
                ", mavenRepoUsername='" + mavenRepoUsername + '\'' +
                ", mavenRepoPassword='" + mavenRepoPassword + '\'' +
                ", deprecatedApiDeletionAllowed=" + deprecatedApiDeletionAllowed +
                ", betaApiExtensionName='" + betaApiExtensionName + '\'' +
                ", apiFilename='" + apiFilename + '\'' +
                ", excludedPaths=" + excludedPaths +
                '}';
    }
}
