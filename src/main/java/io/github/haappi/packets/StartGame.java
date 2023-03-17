package io.github.haappi.packets;

import io.github.haappi.bold_client.HelloApplication;
import io.github.haappi.bold_client.Logger;

import java.io.Serial;

public class StartGame implements Packet {
        @Serial
    private static final long serialVersionUID = 7507038582610105408L;
    @Override
    public void handle() {
        Logger.getInstance().log(this.getClass().getName() + "");
        HelloApplication.getInstance().loadFxmlFile("game-view.fxml");
    }
}
