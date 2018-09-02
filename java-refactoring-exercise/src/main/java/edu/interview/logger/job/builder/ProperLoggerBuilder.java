package edu.interview.logger.job.builder;

import edu.interview.logger.job.FormalJobLogger;
import edu.interview.logger.job.enums.LogDestination;
import edu.interview.logger.job.enums.LogType;

public class ProperLoggerBuilder implements LoggerBuilder {

    private FormalJobLogger logger;
    private String NULL_NOT_ALLOWED = "Null argument is not allowed!";

    public ProperLoggerBuilder() {
        logger = new FormalJobLogger();
    }

    @Override
    public LoggerBuilder withLogTo(LogDestination destination) {
        if (destination == null) throw new IllegalArgumentException(NULL_NOT_ALLOWED);
        logger.enableLogTo(destination, Boolean.TRUE);
        return this;
    }

    @Override
    public LoggerBuilder withLogType(LogType type) {
        if (type == null) throw new IllegalArgumentException(NULL_NOT_ALLOWED);
        logger.enableLogType(type, Boolean.TRUE);
        return this;
    }

    @Override
    public FormalJobLogger build() {
        return logger;
    }

}
