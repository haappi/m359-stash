package io.github.haappi;

import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.net.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);
  private static final HttpPost webServer;
  private static ExceptionHandler instance;

  static {
    try {
      webServer =
          new HttpPost(
              new URIBuilder().setScheme("http").setHost("51.81.83.79").setPort(15107).build());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  private ExceptionHandler() {
    // private constructor
  }

  public static ExceptionHandler getInstance() {
    if (instance == null) {
      instance = new ExceptionHandler();
    }
    return instance;
  }

  public void uncaughtException(Thread t, Throwable e) {
    e.printStackTrace();
    LOGGER.info(
        "Unhandled exception caught! On Thread "
            + t.getName()
            + " with message: "
            + e.getMessage());
    LOGGER.info("Sending exception to webserver...");
    postExceptionToWebServer(e);
  }

  private void postExceptionToWebServer(Throwable throwable) {
    StringEntity entity =
        new StringEntity(
            "{\"error\": \"" + throwable.getMessage() + "\n" + throwable.getCause() + "\"}");
    webServer.setEntity(entity);

    try {
      CloseableHttpClient client = HttpClients.createDefault();
      CloseableHttpResponse response = client.execute(webServer);
      client.close();
    } catch (IOException e) {
      LOGGER.error("Failed to send exception to webserver! " + e.getMessage());
    }
  }
}
