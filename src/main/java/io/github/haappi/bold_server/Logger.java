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
    private static final java.util.logging.Logger LOGGER =
            java.util.logging.Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    private static Logger instance;
    private static final StackWalker walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
    //https://stackoverflow.com/questions/51768011/how-can-i-get-the-caller-class-object-from-a-method-in-java

    private Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        log(walker.getCallerClass(), message, GREEN);
    }

    private void log(Class<?> caller, String message, String color) {
        LOGGER.info("[" + caller + "] " + color + message + RESET);
    }

    public void log(String message, String color) {
        log(walker.getCallerClass(), message, color);
    }
}
