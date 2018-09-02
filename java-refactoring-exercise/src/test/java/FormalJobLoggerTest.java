import edu.interview.logger.job.FormalJobLogger;
import edu.interview.logger.job.builder.ProperLoggerBuilder;
import edu.interview.logger.job.exception.LoggerException;
import org.junit.Test;

import static edu.interview.logger.job.enums.LogType.*;

public class FormalJobLoggerTest {

    @Test
    public void emptyMessageTest() {
        ProperLoggerBuilder builder = new ProperLoggerBuilder();
        FormalJobLogger logger = builder.build();
        logger.logMessage("", MESSAGE);
    }

    @Test(expected = LoggerException.class)
    public void invalidConfigurationTest() {
        ProperLoggerBuilder builder = new ProperLoggerBuilder();
        FormalJobLogger logger = builder.build();
        logger.logMessage("Some text", MESSAGE);
    }

}
