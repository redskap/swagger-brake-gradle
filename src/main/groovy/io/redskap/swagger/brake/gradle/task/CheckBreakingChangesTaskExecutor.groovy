package io.redskap.swagger.brake.gradle.task

import io.redskap.swagger.brake.gradle.task.starter.StarterFactory
import io.redskap.swagger.brake.runner.exception.LatestArtifactDownloadException
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

class CheckBreakingChangesTaskExecutor {
    private static final Logger logger = Logging.getLogger(CheckBreakingChangesTaskExecutor)

    private final CheckBreakingChangesTaskParameterValidator parameterValidator
    private final StarterFactory starterFactory

    CheckBreakingChangesTaskExecutor(CheckBreakingChangesTaskParameterValidator parameterValidator, StarterFactory starterFactory) {
        this.parameterValidator = parameterValidator
        this.starterFactory = starterFactory
    }

    void execute(CheckBreakingChangesTaskParameter parameter) {
        logger.info("The following parameters are set for the task {}", parameter)
        parameterValidator.validate(parameter)
        def options = OptionsFactory.create(parameter)
        try {
            starterFactory.create().start(options)
        } catch (LatestArtifactDownloadException e) {
            logger.quiet("Latest version of the artifact could not be retrieved from {} with {}:{}", options.mavenRepoUrl, options.groupId, options.artifactId)
            logger.quiet("Assuming this is the first version of the artifact, skipping check for breaking changes")
        }
    }
}
