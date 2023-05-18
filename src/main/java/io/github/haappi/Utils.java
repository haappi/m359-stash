package io.github.haappi;

import javafx.application.Platform;

import java.util.HashMap;

public class Utils {
    public static String getHashMapAsJsonString(HashMap<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String key : map.keySet()) {
            sb.append("\"").append(key).append("\":\"").append(map.get(key)).append("\",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

    public static void sleepAndRunLater(long millis, Runnable runnable, boolean javaFxThread) {
        new Thread(() -> {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException: " + e);
            }
            if (javaFxThread) {
                Platform.runLater(runnable);
                return;
            }
            runnable.run();
        }).start();
    }

    public static void sleepAndRunLater(Runnable runnable, boolean javaFXThread) {
        sleepAndRunLater(1000, runnable, javaFXThread);
    }

    public static void sleepAndRunLater(Runnable runnable) {
        sleepAndRunLater(1000, runnable, true);
    }
}
