package io.github.haappi.restaurant_game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionHandler
        implements Thread
                .UncaughtExceptionHandler { // https://www.baeldung.com/java-global-exception-handler

    private static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    public void uncaughtException(Thread t, Throwable e) {
        LOGGER.info("Unhandled exception caught!");
        // show it to the user in the current Controller view like a slide in / out animation or
        // something
        // or basically noithing if i cant figure it out in like 10 minutes.
    }
}
