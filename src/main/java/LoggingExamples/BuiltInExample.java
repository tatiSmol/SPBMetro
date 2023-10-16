package LoggingExamples;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BuiltInExample {
    // встроенная библиотека логирования
    private static final Logger logger = Logger.getLogger(BuiltInExample.class.getName());

    public void doSomething() {
        logger.log(Level.INFO, "Starting doSomething() method");
        // some code
        logger.log(Level.INFO, "Finished doSomething() method");
    }
}
