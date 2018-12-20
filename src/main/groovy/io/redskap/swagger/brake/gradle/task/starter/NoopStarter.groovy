package io.redskap.swagger.brake.gradle.task.starter

import io.redskap.swagger.brake.runner.Options
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

class NoopStarter implements Starter {
    private static final Logger logger = Logging.getLogger(NoopStarter)

    @Override
    void start(Options options) {
        logger.quiet("Doing nothing in noop mode")
    }
}
