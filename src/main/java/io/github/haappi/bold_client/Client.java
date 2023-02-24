package io.github.haappi.bold_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public void connect(String serverAddress, int serverPort) throws IOException {
        clientSocket = new Socket(serverAddress, serverPort);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void sendMessage(String message) {
        out.println(message);
        out.flush(); // Flushing is just to make sure that the message is sent
    }

    public void sendObject(Object object) {
        out.println(object);
        out.flush(); // Flushing is just to make sure that the object is sent
        // (i think it's necessary so i added it :D)

    }

    public String receiveMessage() throws IOException {
        return in.readLine();
    }

    public void close() throws IOException {
        clientSocket.close();
    }
}
