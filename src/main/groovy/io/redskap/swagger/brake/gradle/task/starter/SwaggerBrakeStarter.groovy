package io.redskap.swagger.brake.gradle.task.starter

import io.redskap.swagger.brake.runner.Options

class SwaggerBrakeStarter implements Starter {
    @Override
    void start(Options options) {
        io.redskap.swagger.brake.runner.Starter.start(options)
    }
}
