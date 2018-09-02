package edu.interview.logger.job.builder;

import edu.interview.logger.job.FormalJobLogger;
import edu.interview.logger.job.enums.LogDestination;
import edu.interview.logger.job.enums.LogType;

public class ProperLoggerBuilder implements LoggerBuilder {

    private FormalJobLogger logger;

    public ProperLoggerBuilder() {
        logger = new FormalJobLogger();
    }

    @Override
    public LoggerBuilder withLogTo(LogDestination destination) {
        logger.enableLogTo(destination, Boolean.TRUE);
        return this;
    }

    @Override
    public LoggerBuilder withLogType(LogType type) {
        logger.enableLogType(type, Boolean.TRUE);
        return this;
    }

    @Override
    public FormalJobLogger build() {
        return logger;
    }

}
