package io.github.haappi.bold_server;

import io.github.haappi.packets.CloseServer;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private final ServerSocket serverSocket;
    private final ArrayList<ClientHandler> clients = new ArrayList<>();
    private final int portListening;
    private final String ipListening;
    private final String name;
    private Bold gameInstance;


    private boolean isAcceptingConnections = true;

    public Server(String name, int portListening, String ipListening) throws IOException {
        this.name = name;
        this.portListening = portListening;
        this.ipListening = ipListening;
        serverSocket = new ServerSocket(portListening, 0, Inet4Address.getByName(ipListening));

        this.gameInstance = new Bold(this);
    }

    public Bold getGameInstance() {
        return gameInstance;
    }

    public Server(String name, int portListening) throws IOException {
        this(name, portListening, InetAddress.getLocalHost().getHostAddress());
    }

    public Server() throws IOException {
        this("Server - " + System.currentTimeMillis(), 2005, "localhost");
    }

    public boolean toggleAcceptingConnections() {
        return isAcceptingConnections = !isAcceptingConnections;
    }

    public boolean isAcceptingConnections() {
        return isAcceptingConnections;
    }

    public void close() throws IOException {
        broadcast(new CloseServer());
        this.serverSocket.close();
    }

    public String getName() {
        return name;
    }

    public void start() {
        Logger.getInstance()
                .log(
                        "Starting server "
                                + this.name
                                + " on "
                                + this.ipListening
                                + ":"
                                + this.portListening);
        try {
            while (true) {
                if (!isAcceptingConnections) continue;
                Socket client = serverSocket.accept();
                ClientHandler handler = new ClientHandler(client, this);
                clients.add(handler);
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

    public void broadcast(Object object, ClientHandler sender) throws IOException {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendObject(object);
            }
        }
    }

    public void broadcast(Object object) throws IOException {
        for (ClientHandler client : clients) {
            client.sendObject(object);
        }
    }

    public String toString() {
        return this.name
                + " ("
                + this.ipListening
                + ":"
                + this.portListening
                + ")"
                + " - "
                + clients.size()
                + " clients";
    }

    public ArrayList<ClientHandler> getClients() {
        return clients;
    }

    public int getPortListening() {
        return portListening;
    }

    public String getIpListening() {
        return ipListening;
    }
}
