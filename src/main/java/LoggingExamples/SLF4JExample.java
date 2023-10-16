package LoggingExamples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLF4JExample {
    // SLF4J - Simple Logging Facade for Java
    private static final Logger logger = LoggerFactory.getLogger(SLF4JExample.class);

    public void doSomething() {
        logger.info("Starting doSomething() method");
        // some code
        logger.info("Finished doSomething() method");
    }
}
