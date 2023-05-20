package io.github.haappi;

import java.util.List;
import java.util.Map;
import javafx.application.Platform;
import org.json.JSONObject;

public class Utils {
  public static <E> String jsonString(Map<E, E> data) {
    return new JSONObject(data).toString();
  }

  public static <E> String jsonString(List<E> data) {
    return new JSONObject(data).toString();
  }

  public static <E> String jsonString(E data) {
    return new JSONObject(data).toString();
  }

  public static <E> String jsonString(E[] data) {
    return new JSONObject(data).toString();
  }

  public static void sleepAndRunLater(long millis, Runnable runnable, boolean javaFxThread) {
    new Thread(
            () -> {
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
            })
        .start();
  }

  public static void sleepAndRunLater(Runnable runnable, boolean javaFXThread) {
    sleepAndRunLater(1000, runnable, javaFXThread);
  }

  public static void sleepAndRunLater(Runnable runnable) {
    sleepAndRunLater(1000, runnable, true);
  }
}
