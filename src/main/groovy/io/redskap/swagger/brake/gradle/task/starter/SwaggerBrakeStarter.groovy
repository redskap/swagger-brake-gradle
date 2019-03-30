package io.redskap.swagger.brake.gradle.task.starter

import io.redskap.swagger.brake.core.BreakingChange
import io.redskap.swagger.brake.runner.Options

class SwaggerBrakeStarter implements Starter {
    @Override
    Collection<BreakingChange> start(Options options) {
        return io.redskap.swagger.brake.runner.Starter.start(options)
    }
}
