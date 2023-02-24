package io.github.haappi.bold_server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import socketfx.Constants;
import socketfx.FxSocketServer;
import socketfx.SocketListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class HelloController implements Initializable {
    boolean areReady = false;
    boolean clientReady = false;

    @FXML private Button sendButton, ready;
    @FXML private TextField sendTextField;
    @FXML private Button connectButton;
    @FXML private TextField portTextField;
    @FXML private Label lblMessages;
    @FXML private ImageView mainCardImgView;
    Image imageM;

    private static final Logger LOGGER =
            Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    private boolean isConnected;
    private int counter = 0;
    private String color;

    public enum ConnectionDisplayState {
        DISCONNECTED,
        WAITING,
        CONNECTED,
        AUTOCONNECTED,
        AUTOWAITING
    }

    private FxSocketServer socket;

    private void connect() {
        socket =
                new FxSocketServer(
                        new FxSocketListener(),
                        Integer.valueOf(portTextField.getText()),
                        Constants.instance().DEBUG_NONE);
        socket.connect();
    }

    private void displayState(ConnectionDisplayState state) {
        //        switch (state) {
        //            case DISCONNECTED:
        //                connectButton.setDisable(false);
        //                sendButton.setDisable(true);
        //                sendTextField.setDisable(true);
        //                break;
        //            case WAITING:
        //            case AUTOWAITING:
        //                connectButton.setDisable(true);
        //                sendButton.setDisable(true);
        //                sendTextField.setDisable(true);
        //                break;
        //            case CONNECTED:
        //                connectButton.setDisable(true);
        //                sendButton.setDisable(false);
        //                sendTextField.setDisable(false);
        //                break;
        //            case AUTOCONNECTED:
        //                connectButton.setDisable(true);
        //                sendButton.setDisable(false);
        //                sendTextField.setDisable(false);
        //                break;
        //        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isConnected = false;
        displayState(ConnectionDisplayState.DISCONNECTED);

        Runtime.getRuntime().addShutdownHook(new ShutDownThread());

        /*
         * Uncomment to have autoConnect enabled at startup
         */
        //        autoConnectCheckBox.setSelected(true);
        //        displayState(ConnectionDisplayState.WAITING);
        //        connect();
    }

    class ShutDownThread extends Thread {

        @Override
        public void run() {
            if (socket != null) {
                if (socket.debugFlagIsSet(Constants.instance().DEBUG_STATUS)) {
                    LOGGER.info("ShutdownHook: Shutting down Server Socket");
                }
                socket.shutdown();
            }
        }
    }

    @FXML
    private void handleConnectButton(ActionEvent event) {
        connectButton.setDisable(true);

        displayState(ConnectionDisplayState.WAITING);
        connect();
    }
    // ****************************************************************
    class FxSocketListener implements SocketListener {

        @Override
        public void onMessage(String line) {
            System.out.println("message received client");
            lblMessages.setText(line);
            if (line.equals("ready") && areReady) {

                ready.setVisible(false);
                initGame();
            } else if (line.equals("ready")) {
                clientReady = true;
            }
        }

        @Override
        public void onClosedStatus(boolean isClosed) {}
    }

    @FXML
    private void handleSendMessageButton(ActionEvent event) {
        if (!sendTextField.getText().equals("")) {
            socket.sendMessage(sendTextField.getText());
            System.out.println("Message sent client");
        }
    }

    @FXML
    protected void handleDraw() {
        System.out.println("hi");
    }

    @FXML
    private void handleReady(ActionEvent event) {
        //        if (!sendTextField.getText().equals("")) {
        //            String x = sendTextField.getText();
        //            socket.sendMessage(x);
        //            System.out.println("sent message client");
        //        }
        areReady = true;
        socket.sendMessage("ready");
        if (clientReady) {
            ready.setVisible(false);
            initGame();
        } else {
            ready.setDisable(true);
        }
    }

    public void initGame() {}

    @FXML
    private void handleDeal(ActionEvent event) {
        hand1I.add(imgS0);
        hand1I.add(imgS1);
        hand1I.add(imgS2);
        hand1I.add(imgS3);
        hand1I.add(imgS4);
        hand1I.add(imgS5);
        hand1I.add(imgS6);
        hand1I.add(imgS7);
        hand1I.add(imgS8);
        hand1I.add(imgS9);

        hand2I.add(imgC0);
        hand2I.add(imgC1);
        hand2I.add(imgC2);
        hand2I.add(imgC3);
        hand2I.add(imgC4);
        hand2I.add(imgC5);
        hand2I.add(imgC6);
        hand2I.add(imgC7);
        hand2I.add(imgC8);
        hand2I.add(imgC9);

        imgC0.setImage(imageBack);
        imgC1.setImage(imageBack);
        imgC2.setImage(imageBack);
        imgC3.setImage(imageBack);
        imgC4.setImage(imageBack);
        imgC5.setImage(imageBack);
        imgC6.setImage(imageBack);
        imgC7.setImage(imageBack);

        deck.clear();
        // populate deck
        for (int i = 1; i < 14; i++) {

            deck.add(new Card("C" + Integer.toString(i + 1)));
            deck.add(new Card("S" + Integer.toString(i + 1)));
            deck.add(new Card("H" + Integer.toString(i + 1)));
            deck.add(new Card("D" + Integer.toString(i + 1)));
        }

        // deal each hand
        hand2D.clear();
        hand1D.clear();
        // deal server hand
        System.out.println("Server hand");
        for (int i = 0; i < 7; i++) {
            int y = (int) (Math.random() * (52 - i));
            try {
                tempCard = new FileInputStream(deck.get(y).getCardPath());
                imageFront = new Image(tempCard);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            hand1I.get(i).setImage(imageFront);
            System.out.println(deck.get(y).getCardPath());
            hand1D.add(deck.remove(y));
        }
        // deal client hand
        System.out.println("Client hand");
        for (int i = 0; i < 7; i++) {
            int y = (int) (Math.random() * (45 - i));
            System.out.println(deck.get(y).getCardPath());
            hand2D.add(deck.remove(y));
        }
        socket.sendMessage("dealt");
        sendHandClient();
    }

    public void sendHandClient() {
        String temp = "cCards";
        socket.sendMessage("cCardStart");
        for (Card x : hand2D) {
            temp += "," + x.getCardName();
            socket.sendMessage("cCards" + x.getCardName());
        }
        socket.sendMessage("sCardNum" + hand1D.size());
        sendFirstCard();
    }

    private void sendFirstCard() {
        socket.sendMessage("topCard" + deck.get(0).getCardName());
        try {
            tempCard = new FileInputStream(deck.get(0).getCardPath());
            imageM = new Image(tempCard);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mainCardImgView.setImage(imageM);
    }

    public HelloController() {
        try {
            back1 = new FileInputStream("src/main/resources/Images/BACK-1.jpg");
            imageBack = new Image(back1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private ImageView imgS0,
            imgS1,
            imgS2,
            imgS3,
            imgS4,
            imgS5,
            imgS6,
            imgS7,
            imgS8,
            imgS9,
            imgC0,
            imgC1,
            imgC2,
            imgC3,
            imgC4,
            imgC5,
            imgC6,
            imgC7,
            imgC8,
            imgC9;
    FileInputStream back1, tempCard;
    Image imageBack;
    Image imageFront;
    List<Card> deck = new ArrayList<>();
    Card discard;
    List<ImageView> hand1I = new ArrayList<>();
    List<ImageView> hand2I = new ArrayList<>();
    List<Card> hand1D = new ArrayList<>();
    List<Card> hand2D = new ArrayList<>();
}
