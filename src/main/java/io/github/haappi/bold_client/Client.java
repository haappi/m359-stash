package io.github.haappi.bold_client;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private ObjectOutputStream
            objectStream; // This is the output stream to the client (objects are "written" through this)
    private ObjectInputStream
            objectInputStream; // This is the input stream from the client (objects are "read" through this)

    public void connect(String serverAddress, int serverPort) throws IOException {
        clientSocket = new Socket(serverAddress, serverPort);

        objectStream = new ObjectOutputStream(clientSocket.getOutputStream());
        objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

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