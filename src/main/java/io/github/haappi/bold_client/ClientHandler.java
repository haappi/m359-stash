package io.github.haappi.bold_client;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    // Im extending this class because I want this to run in another thread (without more boilerplate from me) (so if one client
    // disconnects, the server can still handle other clients)
    // and I want to be able to call the method "stop" on this class to stop the thread (if they
    // disconnect)

    private final Socket bindedTo;
    private final Server server;

    private ObjectOutputStream
            objectStream; // This is the output stream to the client (objects are "written" through this)
    private ObjectInputStream
            objectInputStream; // This is the input stream from the client (objects are "read" through this)

    public ClientHandler(Socket bindedTo, Server server) {
        Logger.getInstance().log("Client connected: " + bindedTo.getInetAddress().getHostAddress() + ":" + bindedTo.getPort(), Logger.YELLOW);
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
        objectStream.writeObject(object);
        objectStream.flush(); // Flushing is just to make sure that the object is sent
        // (i think it's necessary so i added it :D)
    }

    @Override
    public void run() {
        try {
            // while loop and keep listenting for objects
            while (true) {
                Object object = objectInputStream.readObject();
                System.out.println(((Test) object).getAge());
//                if (object instanceof Packet) {
//                    ((Packet) object).handle(this);
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() throws IOException {
        bindedTo.close();
    }
}
