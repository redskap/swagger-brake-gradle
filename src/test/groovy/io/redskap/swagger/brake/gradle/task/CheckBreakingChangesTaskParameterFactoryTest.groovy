package io.redskap.swagger.brake.gradle.task

import org.gradle.api.Project
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import spock.lang.Specification

class CheckBreakingChangesTaskParameterFactoryTest extends Specification {
    private Project project = Mock(Project)
    private Property<String> newApi = Mock(Property)
    private Property<String> oldApi = Mock(Property)
    private Property<String> mavenRepoUrl = Mock(Property)
    private Property<String> mavenSnapshotRepoUrl = Mock(Property)
    private Property<String> artifactId = Mock(Property)
    private Property<String> groupId = Mock(Property)
    private Property<String> currentVersion = Mock(Property)
    private Property<String> outputFilePath = Mock(Property)
    private ListProperty<String> outputFormats = Mock(ListProperty)
    private Property<String> mavenRepoUsername = Mock(Property)
    private Property<String> mavenRepoPassword = Mock(Property)
    private Property<Boolean> deprecatedApiDeletionAllowed = Mock(Property)
    private Property<String> betaApiExtensionName = Mock(Property)
    private Property<String> apiFilename = Mock(Property)

    private CheckBreakingChangesTaskParameterFactory underTest = new CheckBreakingChangesTaskParameterFactory()

    def "create works when everything is set in properties"() {
        given:
        def newApiVal = "newApi"
        def oldApiVal = "oldApi"
        def mavenRepoUrlVal = "mavenRepoUrl"
        def mavenSnapshotRepoUrlVal = "mavenSnapshotRepoUrl"
        def mavenRepoUsernameVal = "username"
        def mavenRepoPasswordVal = "password"
        def artifactIdVal = "artifactId"
        def groupIdVal = "groupId"
        def currentVersionVal = "1.0.0"
        def outputFilePathVal = "outputFilePath"
        def outputFormatsVal = ["HTML", "JSON"]
        def deprecatedApiDeletionAllowedVal = true
        def betaApiExtensionNameVal = "betaApiExtensionName"
        def apiFilenameVal = "apiFilename"

        and:
        newApi.get() >> newApiVal
        oldApi.getOrElse(null) >> oldApiVal
        mavenRepoUrl.getOrElse(null) >> mavenRepoUrlVal
        mavenSnapshotRepoUrl.getOrElse(null) >> mavenSnapshotRepoUrlVal
        artifactId.getOrElse(null) >> artifactIdVal
        groupId.getOrElse(null) >> groupIdVal
        currentVersion.getOrElse(null) >> currentVersionVal
        outputFilePath.getOrElse(null) >> outputFilePathVal
        outputFormats.getOrElse([]) >> outputFormatsVal
        mavenRepoUsername.getOrElse(null) >> mavenRepoUsernameVal
        mavenRepoPassword.getOrElse(null) >> mavenRepoPasswordVal
        deprecatedApiDeletionAllowed.getOrElse(false) >> deprecatedApiDeletionAllowedVal
        betaApiExtensionName.getOrElse(null) >> betaApiExtensionNameVal
        apiFilename.getOrElse(null) >> apiFilenameVal


        when:
        def result = CheckBreakingChangesTaskParameterFactory.create(
                project,
                newApi,
                oldApi,
                mavenRepoUrl,
                mavenSnapshotRepoUrl,
                artifactId,
                groupId,
                currentVersion,
                outputFilePath,
                outputFormats,
                mavenRepoUsername,
                mavenRepoPassword,
                deprecatedApiDeletionAllowed,
                betaApiExtensionName,
                apiFilename
        )
        
        then:
        assert result.newApi == newApiVal
        assert result.oldApi == oldApiVal
        assert result.mavenRepoUrl == mavenRepoUrlVal
        assert result.mavenSnapshotRepoUrl == mavenSnapshotRepoUrlVal
        assert result.artifactId == artifactIdVal
        assert result.groupId == groupIdVal
        assert result.currentVersion == currentVersionVal
        assert result.outputFilePath == outputFilePathVal
        assert result.outputFormats == outputFormatsVal
        assert result.mavenRepoUsername == mavenRepoUsernameVal
        assert result.mavenRepoPassword == mavenRepoPasswordVal
        assert result.deprecatedApiDeletionAllowed == deprecatedApiDeletionAllowedVal
        assert result.betaApiExtensionName == betaApiExtensionNameVal
        assert result.apiFilename == apiFilenameVal
    }


    def "create works when the project is used for properties and defaults are applied"() {
        given:
        def newApiVal = "newApi"
        def mavenRepoUrlVal = "mavenRepoUrl"
        def mavenSnapshotRepoUrlVal = "mavenSnapshotRepoUrl"
        def artifactIdVal = "artifactId"
        def groupIdVal = "groupId"
        def currentVersionVal = "1.0.0"
        def outputFilePathVal = "outputFilePath"
        def buildDir = new File(outputFilePathVal)
        def deprecatedApiDeletionAllowedVal = false

        and:
        newApi.get() >> newApiVal
        mavenRepoUrl.getOrElse(null) >> mavenRepoUrlVal
        mavenSnapshotRepoUrl.getOrElse(null) >> mavenSnapshotRepoUrlVal
        artifactId.getOrElse(null) >> null
        groupId.getOrElse(null) >> null
        currentVersion.getOrElse(null) >> null
        outputFilePath.getOrElse(null) >> null
        deprecatedApiDeletionAllowed.getOrElse(false) >> deprecatedApiDeletionAllowedVal
        project.getBuildDir() >> buildDir
        project.getGroup() >> groupIdVal
        project.getName() >> artifactIdVal
        project.getVersion() >> currentVersionVal


        when:
        def result = CheckBreakingChangesTaskParameterFactory.create(
                project,
                newApi,
                oldApi,
                mavenRepoUrl,
                mavenSnapshotRepoUrl,
                artifactId,
                groupId,
                currentVersion,
                outputFilePath,
                outputFormats,
                mavenRepoUsername,
                mavenRepoPassword,
                deprecatedApiDeletionAllowed,
                betaApiExtensionName,
                apiFilename
        )

        then:
        assert result.newApi == newApiVal
        assert result.mavenRepoUrl == mavenRepoUrlVal
        assert result.mavenSnapshotRepoUrl == mavenSnapshotRepoUrlVal
        assert result.artifactId == artifactIdVal
        assert result.groupId == groupIdVal
        assert result.currentVersion == currentVersionVal
        assert result.outputFilePath == "${outputFilePathVal}/swagger-brake/"
        assert result.outputFormats == ["HTML"]
        assert result.deprecatedApiDeletionAllowed == deprecatedApiDeletionAllowedVal
    }
}
