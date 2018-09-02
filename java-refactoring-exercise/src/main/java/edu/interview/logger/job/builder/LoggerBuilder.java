package edu.interview.logger.job.builder;

import edu.interview.logger.job.FormalJobLogger;
import edu.interview.logger.job.enums.LogDestination;
import edu.interview.logger.job.enums.LogType;

public interface LoggerBuilder {

    LoggerBuilder withLogTo(LogDestination destination);
    LoggerBuilder withLogType(LogType type);
    FormalJobLogger build();

}
