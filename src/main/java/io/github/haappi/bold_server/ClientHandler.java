package io.github.haappi.bold_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    // Im extending this class because I want this to run in another thread (so if one client
    // disconnects, the server can still handle other clients)
    // and I want to be able to call the method "stop" on this class to stop the thread (if they
    // disconnect)

    private final Socket bindedTo;
    private final Server server;

    private BufferedReader
            in; // This is the input stream from the client (messages are "buffered" through this)
    private PrintWriter
            out; // This is the output stream to the client (messages are "written" through this)

    public ClientHandler(Socket bindedTo, Server server) {
        this.bindedTo = bindedTo;
        this.server = server;

        try {
            in = new BufferedReader(new InputStreamReader(bindedTo.getInputStream()));
            out = new PrintWriter(bindedTo.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMessage(String message) {
        out.println(message);
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(
                        "Client " + bindedTo.getInetAddress().getHostAddress() + " sent: " + line);
                server.broadcastMessage(line, this); // Send the message to all OTHER clients
            }
            // if we make it here, the client disconnected
            server.removeClient(this);
            System.out.println(
                    "Client " + bindedTo.getInetAddress().getHostAddress() + " disconnected");
            bindedTo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
