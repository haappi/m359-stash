package io.github.haappi.webserver;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class BasicWebserver {
    HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);

    public BasicWebserver() throws IOException {
        server.createContext("/error", new CustomHttpHandler());
        server.setExecutor((ThreadPoolExecutor) Executors.newFixedThreadPool(10));
        server.start();

    }
}
