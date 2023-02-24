package io.github.haappi.bold_client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import socketfx.Constants;
import socketfx.FxSocketClient;
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
    boolean serverReady = false;
    @FXML
    private Button sendButton, ready;
    @FXML
    private TextField sendTextField;
    @FXML
    private Button connectButton;
    @FXML
    private TextField portTextField;
    @FXML
    private TextField hostTextField;
    @FXML
    private Label lblName1, lblName2, lblName3, lblName4, lblMessages;

    @FXML
    private GridPane gPaneServer, gPaneClient;


    private final static Logger LOGGER =
            Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    private boolean isConnected, turn, serverUNO = false, clientUNO = false;
    public enum ConnectionDisplayState {
        DISCONNECTED, WAITING, CONNECTED, AUTOCONNECTED, AUTOWAITING
    }
    private FxSocketClient socket;
    private void connect() {
        socket = new FxSocketClient(new FxSocketListener(),
                hostTextField.getText(),
                Integer.valueOf(portTextField.getText()),
                Constants.instance().DEBUG_NONE);
        socket.connect();
    }
    private void displayState(ConnectionDisplayState state) {
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isConnected = false;
        displayState(ConnectionDisplayState.DISCONNECTED);
        Runtime.getRuntime().addShutdownHook(new ShutDownThread());
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
    private ImageView imgS0,imgS1,imgS2,imgS3,imgS4,imgS5,imgS6,imgS7,imgS8,imgS9,
    imgC0,imgC1,imgC2,imgC3,imgC4, imgC5,imgC6,imgC7,imgC8,imgC9;
    FileInputStream back1,tempCard;
    Image imageBack;
    Image imageFront;
    List<ImageView> hand1I = new ArrayList<>();
    List<ImageView> hand2I = new ArrayList<>();
    List<Card> hand1D = new ArrayList<>();
    List<Card> hand2D = new ArrayList<>();
    int numInServerHand=0;
    public HelloController(){
        try {
            back1 = new FileInputStream("src/main/resources/Images/BACK-1.jpg");
            imageBack = new Image(back1);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    class FxSocketListener implements SocketListener {

        @Override
        public void onMessage(String line) {
            System.out.println("message received server");
            lblMessages.setText(line);
            if (line.equals("ready") && areReady){
                ready.setVisible(false);
            } else if(line.equals("ready")){
                serverReady=true;
            }
            else if(line.equals("dealt")){
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

                imgS0.setImage(imageBack);
                imgS1.setImage(imageBack);
                imgS2.setImage(imageBack);
                imgS3.setImage(imageBack);
                imgS4.setImage(imageBack);
                imgS5.setImage(imageBack);
                imgS6.setImage(imageBack);
                imgS7.setImage(imageBack);

            }else if(line.equals("cCardStart")){
                hand2D.clear();
            }
            else if (line.startsWith("cCards")){
                hand2D.add(new Card(line.substring(6)));

            } else if(line.startsWith("sCardNum")){
                numInServerHand = Integer.parseInt(line.substring(8));
                printCCards();
                System.out.println(numInServerHand);
            }
        }

        public void printCCards(){
            for (int i=0;i<hand2D.size();i++){
                System.out.println(hand2D.get(i).getCardPath());
                try {
                    tempCard = new FileInputStream(hand2D.get(i).getCardPath());
                    imageFront = new Image(tempCard);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                hand2I.get(i).setImage(imageFront);
            }
        }
        @Override
        public void onClosedStatus(boolean isClosed) {

        }
    }
    @FXML
    private void handleReady(ActionEvent event) {
//        if (!sendTextField.getText().equals("")) {
//            String x = sendTextField.getText();
//            socket.sendMessage(x);
//            System.out.println("sent message client");
//        }
        areReady=true;
        socket.sendMessage("ready");
        if (serverReady){
            ready.setVisible(false);
        }else{
            ready.setDisable(true);
        }
    }





    @FXML
    private void handleConnectButton(ActionEvent event) {
        connectButton.setDisable(true);
        displayState(ConnectionDisplayState.WAITING);
        connect();
    }

}