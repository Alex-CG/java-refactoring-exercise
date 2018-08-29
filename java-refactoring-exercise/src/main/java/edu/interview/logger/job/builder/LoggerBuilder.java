package edu.interview.logger.job.builder;

import edu.interview.logger.job.FormalJobLogger;
import edu.interview.logger.job.LogDestination;
import edu.interview.logger.job.LogType;

public interface LoggerBuilder {

    LoggerBuilder enableLogTo(LogDestination destination);
    LoggerBuilder enableLogType(LogType type);
    FormalJobLogger build();

}
