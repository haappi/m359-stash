package io.github.haappi.bold_client;

import javafx.application.Platform;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Client {
    private Socket clientSocket;
    private ObjectOutputStream
            objectStream; // This is the output stream to the client (objects are "written" through
    // this)
    private ObjectInputStream
            objectInputStream; // This is the input stream from the client (objects are "read"
    // through this)

    private final String name;
    private static Client instance;

    private Client(String name) {
        if (name == null || name.equals("")) {
            name = "Client-" + (int) (Math.random() * 1000);
        }
        this.name = name;
    }

    public static synchronized @NotNull Client getInstance(@Nullable String name) {
        if (instance == null) {
            instance = new Client(name);
        }
        return instance;
    }

    public static synchronized @NotNull Client getInstance() {
        return getInstance(null);
    }

    public void reset() {
        instance = null;
        instance = new Client(name);
    }

    public void connect(String serverAddress, int serverPort) throws IOException {
        clientSocket = new Socket(serverAddress, serverPort);

        objectStream = new ObjectOutputStream(clientSocket.getOutputStream());
        objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

        sendMessage("playerName:" + name);

        //        sendObject(
        //                new Hello(
        //                        name,
        //                        clientSocket.getInetAddress().getHostAddress(),
        //                        clientSocket.getPort(),
        //                        clientSocket.getRemoteSocketAddress().toString().split("/")[1]));

        // start listening to messages from the server
        new Thread(
                        () -> {
                            try {
                                while (true) {
                                    Object object = objectInputStream.readObject();
                                    if (object instanceof String) {
                                        System.out.println("Reading String");
                                        Platform.runLater(
                                                () -> GameView.messageReceived((String) object));
                                    } else {
                                        System.out.println("Reading object");
                                        Platform.runLater(() -> GameView.messageReceived(object));
                                    }
                                }
                            } catch (EOFException | SocketException e) {
                                System.out.println("Client disconnected");
                                HelloApplication.getInstance().loadFxmlFile("connect-menu.fxml");
                                // This is thrown when the client disconnects
                                // (EOFException is thrown when the server disconnec
                            } catch (IOException | ClassNotFoundException e) {
                                e.printStackTrace();
                                //
                            }
                        })
                .start();
    }

    public void sendMessage(Object object) {
        if (objectStream == null) {
            return;
        }

        try {
            objectStream.writeObject(object);
            objectStream.flush(); // Flushing is just to make sure that the object is sent
        } catch (IOException e) {
            e.printStackTrace();
        }
        // (i think it's necessary so i added it :D)
    }

    public void sendMessage(String message) {
        this.sendMessage((Object) message);
    }

    public String clientName() {
        if (clientSocket == null) {
            return "Client";
        }
        return clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort();
    }

    public String getServerAddress() {
        if (clientSocket == null) {
            return "localhost";
        }
        return clientSocket.getRemoteSocketAddress().toString().split("/")[1];
    }

    public void close() throws IOException {
        if (clientSocket == null) {
            return;
        }
        clientSocket.close();
    }
}