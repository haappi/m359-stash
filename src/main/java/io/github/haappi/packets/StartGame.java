package io.github.haappi.packets;

import io.github.haappi.bold_client.HelloApplication;
import io.github.haappi.bold_client.Logger;

public class StartGame implements Packet {
    @Override
    public void handle() {
        Logger.getInstance().log(this.getClass().getName() + "");
        HelloApplication.getInstance().loadFxmlFile("game-view.fxml");
    }
}
