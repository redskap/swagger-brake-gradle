package io.redskap.swagger.brake.gradle.task

class CheckBreakingChangesTaskParameter {
    String newApi
    String mavenRepoUrl
    String groupId
    String artifactId
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
                ", mavenRepoUrl='" + mavenRepoUrl + '\'' +
                ", groupId='" + groupId + '\'' +
                ", artifactId='" + artifactId + '\'' +
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
