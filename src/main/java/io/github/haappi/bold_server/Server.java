package io.github.haappi.bold_server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Server {
    private int portListening;
    private String ipListening;
    private final ServerSocket serverSocket;
    private final ArrayList<ClientHandler> clients = new ArrayList<>();

    public Server(int portListening, String ipListening) throws IOException {
        this.portListening = portListening;
        this.ipListening = ipListening;
        serverSocket = new ServerSocket(portListening, 0, Inet4Address.getByName(ipListening));
    }

    public Server(int portListening) throws IOException {
        this(portListening, InetAddress.getLocalHost().getHostAddress());
    }

    public Server() throws IOException {
        this(2005, "localhost");
    }

    public void start() {
        System.out.println("Server started on " + this.ipListening + ":" + this.portListening);
        try {
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("Client connected: " + client.getInetAddress().getHostAddress());
                ClientHandler handler = new ClientHandler(client, this);
                clients.add(handler);
                handler.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

    public void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.writeMessage(message);
            }
        }
    }
}
