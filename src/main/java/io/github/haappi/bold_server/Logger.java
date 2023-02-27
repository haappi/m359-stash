package io.github.haappi.bold_server;

import java.lang.invoke.MethodHandles;

public class Logger {
    // ANSI escape code
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    private final static java.util.logging.Logger LOGGER =
            java.util.logging.Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    private static Logger instance;

    private Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        log(message, GREEN);
    }

    public void log(String message, String color) {
        LOGGER.info(color + message + RESET);
    }

}

