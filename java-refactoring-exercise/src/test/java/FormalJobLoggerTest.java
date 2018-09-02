import com.sun.javaws.exceptions.InvalidArgumentException;
import edu.interview.logger.job.FormalJobLogger;
import edu.interview.logger.job.builder.ProperLoggerBuilder;
import edu.interview.logger.job.exception.LoggerException;
import org.junit.Test;

import static edu.interview.logger.job.enums.LogType.*;
import static edu.interview.logger.job.enums.LogDestination.*;

public class FormalJobLoggerTest {

    @Test
    public void emptyMessageTest() {
        ProperLoggerBuilder builder = new ProperLoggerBuilder();
        FormalJobLogger logger = builder
                .build();
        logger.logMessage("", null);
    }

    @Test(expected = LoggerException.class)
    public void invalidConfigurationTest() {
        ProperLoggerBuilder builder = new ProperLoggerBuilder();
        FormalJobLogger logger = builder
                .build();
        logger.logMessage("Some text", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidNullLogDestinationTest() {
        ProperLoggerBuilder builder = new ProperLoggerBuilder();
        FormalJobLogger logger = builder
                .withLogTo(null)
                .build();
        logger.logMessage("", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidNullLogTypeTest() {
        ProperLoggerBuilder builder = new ProperLoggerBuilder();
        FormalJobLogger logger = builder
                .withLogType(null)
                .build();
        logger.logMessage("", null);
    }

    @Test(expected = LoggerException.class)
    public void logTypeNotSpecifiedTest() {
        ProperLoggerBuilder builder = new ProperLoggerBuilder();
        FormalJobLogger logger = builder
                .withLogTo(DATABASE)
                .build();
        logger.logMessage("Some text", null);
    }

    @Test(expected = LoggerException.class)
    public void messageTypeNotSpecifiedTest() {
        ProperLoggerBuilder builder = new ProperLoggerBuilder();
        FormalJobLogger logger = builder
                .withLogTo(DATABASE)
                .withLogType(MESSAGE)
                .build();
        logger.logMessage("Some text", null);
    }

}
