package io.redskap.swagger.brake.gradle.task.starter

import io.redskap.swagger.brake.core.BreakingChange
import io.redskap.swagger.brake.runner.Options

interface Starter {
    Collection<BreakingChange> start(Options options)
}
