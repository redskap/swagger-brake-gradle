package io.redskap.swagger.brake.gradle.task

import spock.lang.Specification

class CheckBreakingChangesTaskParameterValidatorTest extends Specification {
    private CheckBreakingChangesTaskParameterValidator underTest = new CheckBreakingChangesTaskParameterValidator()

    def "validate does not throw exception when mavenRepoUrl is set"() {
        given:
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.mavenRepoUrl = "something"
        parameter.newApi = "newApi"
        parameter.artifactId = "artifactId"
        parameter.groupId = "groupId"
        parameter.outputFilePath = "outputFilePath"
        parameter.outputFormat = "html"

        when:
        underTest.validate(parameter)

        then:
        noExceptionThrown()
    }

    def "validate does not throw exception when mavenRepoUrl is set and oldApi is blank"() {
        given:
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.mavenRepoUrl = "something"
        parameter.oldApi = ""
        parameter.newApi = "newApi"
        parameter.artifactId = "artifactId"
        parameter.groupId = "groupId"
        parameter.outputFilePath = "outputFilePath"
        parameter.outputFormat = "html"

        when:
        underTest.validate(parameter)

        then:
        noExceptionThrown()
    }

    def "validate does not throw exception when oldApi is set"() {
        given:
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.oldApi = "something"
        parameter.newApi = "newApi"
        parameter.artifactId = "artifactId"
        parameter.groupId = "groupId"
        parameter.outputFilePath = "outputFilePath"
        parameter.outputFormat = "html"

        when:
        underTest.validate(parameter)

        then:
        noExceptionThrown()
    }

    def "validate does not throw exception when oldApi is set and mavenRepoUrl is blank"() {
        given:
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.mavenRepoUrl = ""
        parameter.oldApi = "something"
        parameter.newApi = "newApi"
        parameter.artifactId = "artifactId"
        parameter.groupId = "groupId"
        parameter.outputFilePath = "outputFilePath"
        parameter.outputFormat = "html"

        when:
        underTest.validate(parameter)

        then:
        noExceptionThrown()
    }

    def "validate throws exception when mavenRepoUrl is null"() {
        given:
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.newApi = "newApi"
        parameter.artifactId = "artifactId"
        parameter.groupId = "groupId"
        parameter.outputFilePath = "outputFilePath"
        parameter.outputFormat = "html"

        when:
        underTest.validate(parameter)

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message.contains('mavenRepoUrl')
    }

    def "validate throws exception when mavenRepoUrl or oldApi are empty"() {
        given:
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.mavenRepoUrl = ""
        parameter.oldApi = ""
        parameter.newApi = "newApi"
        parameter.artifactId = "artifactId"
        parameter.groupId = "groupId"
        parameter.outputFilePath = "outputFilePath"
        parameter.outputFormat = "html"

        when:
        underTest.validate(parameter)

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message.contains('mavenRepoUrl or oldApi')
    }


    def "validate throws exception when mavenRepoUrl and oldApi are both set"() {
        given:
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.mavenRepoUrl = "something"
        parameter.oldApi = "else"
        parameter.newApi = "newApi"
        parameter.artifactId = "artifactId"
        parameter.groupId = "groupId"
        parameter.outputFilePath = "outputFilePath"
        parameter.outputFormat = "html"

        when:
        underTest.validate(parameter)

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message.contains('mavenRepoUrl and oldApi')
    }

    def "validate throws exception when newApi is null"() {
        given:
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.mavenRepoUrl = "repoUrl"
        parameter.artifactId = "artifactId"
        parameter.groupId = "groupId"
        parameter.outputFilePath = "outputFilePath"
        parameter.outputFormat = "html"

        when:
        underTest.validate(parameter)

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message.contains('newApi')
    }

    def "validate throws exception when newApi is empty"() {
        given:
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.mavenRepoUrl = "repoUrl"
        parameter.newApi = ""
        parameter.artifactId = "artifactId"
        parameter.groupId = "groupId"
        parameter.outputFilePath = "outputFilePath"
        parameter.outputFormat = "html"

        when:
        underTest.validate(parameter)

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message.contains('newApi')
    }

    def "validate throws exception when artifactId is null"() {
        given:
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.mavenRepoUrl = "repoUrl"
        parameter.newApi = "newApi"
        parameter.groupId = "groupId"
        parameter.outputFilePath = "outputFilePath"
        parameter.outputFormat = "html"

        when:
        underTest.validate(parameter)

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message.contains('artifactId')
    }

    def "validate throws exception when artifactId is empty"() {
        given:
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.mavenRepoUrl = "repoUrl"
        parameter.newApi = "newApi"
        parameter.artifactId = ""
        parameter.groupId = "groupId"
        parameter.outputFilePath = "outputFilePath"
        parameter.outputFormat = "html"

        when:
        underTest.validate(parameter)

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message.contains('artifactId')
    }

    def "validate throws exception when groupId is null"() {
        given:
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.mavenRepoUrl = "repoUrl"
        parameter.newApi = "newApi"
        parameter.artifactId = "artifactId"
        parameter.outputFilePath = "outputFilePath"
        parameter.outputFormat = "html"

        when:
        underTest.validate(parameter)

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message.contains('groupId')
    }

    def "validate throws exception when groupId is empty"() {
        given:
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.mavenRepoUrl = "repoUrl"
        parameter.newApi = "newApi"
        parameter.artifactId = "artifactId"
        parameter.groupId = ""
        parameter.outputFilePath = "outputFilePath"
        parameter.outputFormat = "html"

        when:
        underTest.validate(parameter)

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message.contains('groupId')
    }

    def "validate throws exception when outputFilePath is null"() {
        given:
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.mavenRepoUrl = "repoUrl"
        parameter.newApi = "newApi"
        parameter.artifactId = "artifactId"
        parameter.groupId = "groupId"
        parameter.outputFormat = "html"

        when:
        underTest.validate(parameter)

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message.contains('outputFilePath')
    }

    def "validate throws exception when outputFilePath is empty"() {
        given:
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.mavenRepoUrl = "repoUrl"
        parameter.newApi = "newApi"
        parameter.artifactId = "artifactId"
        parameter.groupId = "groupId"
        parameter.outputFilePath = ""
        parameter.outputFormat = "html"

        when:
        underTest.validate(parameter)

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message.contains('outputFilePath')
    }

    def "validate throws exception when outputFormat is null"() {
        given:
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.mavenRepoUrl = "repoUrl"
        parameter.newApi = "newApi"
        parameter.artifactId = "artifactId"
        parameter.groupId = "groupId"
        parameter.outputFilePath = "outputFilePath"

        when:
        underTest.validate(parameter)

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message.contains('outputFormat')
    }

    def "validate throws exception when outputFormat is empty"() {
        given:
        def parameter = new CheckBreakingChangesTaskParameter()
        parameter.mavenRepoUrl = "repoUrl"
        parameter.newApi = "newApi"
        parameter.artifactId = "artifactId"
        parameter.groupId = "groupId"
        parameter.outputFilePath = "outputFilePath"
        parameter.outputFormat = ""

        when:
        underTest.validate(parameter)

        then:
        def e = thrown(IllegalArgumentException)
        assert e.message.contains('outputFormat')
    }
}
