package io.github.haappi.bold_server;

import io.github.haappi.packets.Hello;
import io.github.haappi.packets.Packet;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler extends Thread {
    // Im extending this class because I want this to run in another thread (without more
    // boilerplate from me) (so if one client
    // disconnects, the server can still handle other clients)
    // and I want to be able to call the method "stop" on this class to stop the thread (if they
    // disconnect)

    private final Socket bindedTo;
    private final Server server;

    private ObjectOutputStream
            objectStream; // This is the output stream to the client (objects are "written" through
    // this)
    private ObjectInputStream
            objectInputStream; // This is the input stream from the client (objects are "read"
    // through this)

    public ClientHandler(Socket bindedTo, Server server) {
        Logger.getInstance()
                .log(
                        "Client connected: "
                                + bindedTo.getInetAddress().getHostAddress()
                                + ":"
                                + bindedTo.getPort(),
                        Logger.YELLOW);
        this.bindedTo = bindedTo;
        this.server = server;

        try {
            objectStream = new ObjectOutputStream(bindedTo.getOutputStream());
            objectInputStream = new ObjectInputStream(bindedTo.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendObject(Object object) throws IOException {
        try {
            objectStream.writeObject(object);
            objectStream.flush(); // Flushing is just to make sure that the object is sent
        } catch (IOException e) {
            throw new IOException("Failed to send object", e);
        }
        // (i think it's necessary so i added it :D)
    }

    @Override
    public void run() {
        try {
            // while loop and keep listenting for objects
            while (true) {
                try {
                    Object object = objectInputStream.readObject();
                    if (object instanceof Packet) {
                        if (object instanceof Hello hello) {
                            System.out.println(hello.getClientName());
                        }
                    }
                } catch (EOFException | SocketException e) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            this.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() throws IOException {
        Logger.getInstance()
                .log(
                        "Client disconnected: "
                                + bindedTo.getInetAddress().getHostAddress()
                                + ":"
                                + bindedTo.getPort(),
                        Logger.YELLOW);
        bindedTo.close();
    }
}
