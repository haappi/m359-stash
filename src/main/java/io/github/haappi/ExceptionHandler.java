package io.github.haappi;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);
    private static final HttpPost webServer = new HttpPost("");

    public void uncaughtException(Thread t, Throwable e) {
        LOGGER.info("Unhandled exception caught!");
    }
}
