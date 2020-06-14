package io.redskap.swagger.brake.gradle.task

class CheckBreakingChangesTaskParameter {
    String newApi
    String oldApi
    String mavenRepoUrl
    String mavenSnapshotRepoUrl
    String artifactId
    String groupId
    String currentVersion
    String outputFilePath
    String outputFormat
    String mavenRepoUsername
    String mavenRepoPassword
    Boolean deprecatedApiDeletionAllowed
    String betaApiExtensionName
    String apiFilename

    @Override
    String toString() {
        return "CheckBreakingChangesTaskParameter{" +
                "newApi='" + newApi + '\'' +
                ", oldApi='" + oldApi + '\'' +
                ", mavenRepoUrl='" + mavenRepoUrl + '\'' +
                ", mavenSnapshotRepoUrl='" + mavenSnapshotRepoUrl + '\'' +
                ", artifactId='" + artifactId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", currentVersion='" + currentVersion + '\'' +
                ", outputFilePath='" + outputFilePath + '\'' +
                ", outputFormat='" + outputFormat + '\'' +
                ", mavenRepoUsername='" + mavenRepoUsername + '\'' +
                ", mavenRepoPassword='" + mavenRepoPassword + '\'' +
                ", deprecatedApiDeletionAllowed=" + deprecatedApiDeletionAllowed +
                ", betaApiExtensionName='" + betaApiExtensionName + '\'' +
                ", apiFilename='" + apiFilename + '\'' +
                '}';
    }
}
