package io.github.haappi.bold_server;

import io.github.haappi.packets.Packet;
import io.github.haappi.packets.Player;

import java.io.*;
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
    private final String clientName;

    private Player player;

    private ObjectOutputStream
            objectStream; // This is the output stream to the client (objects are "written" through
    // this)
    private ObjectInputStream
            objectInputStream; // This is the input stream from the client (objects are "read"
    // through this)

    public Player getPlayer() {
        return player == null ? new Player(clientName) : player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Server getServer() {
        return server;
    }



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
        this.clientName = bindedTo.getInetAddress().getHostAddress() + ":" + bindedTo.getPort();

        try {
            objectStream = new ObjectOutputStream(bindedTo.getOutputStream());
            objectInputStream = new ObjectInputStream(bindedTo.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendObject(Object object) {
        try {
            objectStream.writeObject(object);
            objectStream.flush(); // Flushing is just to make sure that the object is sent
        } catch (IOException e) {
            return;
        }
        // (i think it's necessary so i added it :D)
    }

    public void sendMessage(String message) {
        this.sendObject(message);
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(bindedTo.getInputStream()));
            // while loop and keep listenting for objects

            while (true) {
                String msg = reader.readLine();
                if (msg == null) {
                    break;
                }
                Logger.getInstance().log("Message received: " + msg + " from " + clientName, Logger.YELLOW);
                server.handleMessageTwo(msg, this);
            }

//            while (true) {
//                try {
//                    Object object = objectInputStream.readObject();
//                    if (object instanceof Packet packet) {
//                        packet.handle(this);
//                    }
//                } catch (EOFException | SocketException e) {
//                    break;
//                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.close();
            server.removeClient(this);
        } catch (IOException e) {
            e.printStackTrace();
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