package edu.interview.logger.job.builder;

import edu.interview.logger.job.FormalJobLogger;
import edu.interview.logger.job.LogDestination;
import edu.interview.logger.job.LogType;

public class ProperLoggerBuilder implements LoggerBuilder {

    private FormalJobLogger logger;

    public ProperLoggerBuilder() {
        logger = new FormalJobLogger();
    }

    @Override
    public LoggerBuilder enableLogTo(LogDestination destination) {
        logger.enableLogTo(destination, true);
        return this;
    }

    @Override
    public LoggerBuilder enableLogType(LogType type) {
        logger.enableLogType(type, true);
        return this;
    }

    @Override
    public FormalJobLogger build() {
        return logger;
    }


}
