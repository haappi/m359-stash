package io.github.haappi.bold_client;

import io.github.haappi.packets.Hello;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private ObjectOutputStream
            objectStream; // This is the output stream to the client (objects are "written" through this)
    private ObjectInputStream
            objectInputStream; // This is the input stream from the client (objects are "read" through this)

    private final String name;

    public Client(String name) {
        if (name == null || name.equals("")) {
            name = "Client";
        }
        this.name = name;
    }

    public void connect(String serverAddress, int serverPort) throws IOException {
        clientSocket = new Socket(serverAddress, serverPort);

        objectStream = new ObjectOutputStream(clientSocket.getOutputStream());
        objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

        sendObject(new Hello(name, clientSocket.getInetAddress().getHostAddress(), clientSocket.getPort()));

    }

    public void sendObject(Object object) throws IOException {
        objectStream.writeObject(object);
        objectStream.flush(); // Flushing is just to make sure that the object is sent
        // (i think it's necessary so i added it :D)

    }

    public void close() throws IOException {
        clientSocket.close();
    }
}
