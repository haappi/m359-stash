package io.github.haappi.packets;

import io.github.haappi.bold_server.ClientHandler;
import io.github.haappi.bold_server.HelloApplication;
import io.github.haappi.bold_server.Server;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Objects;

public class Ready implements Packet {
    @Serial private static final long serialVersionUID = -5058600322899240319L;
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public Ready(Player player) {
        this.player = player;
    }

    @Override
    public void handle(ClientHandler client) {
        for (Server server : HelloApplication.getInstance().getServers()) {
            for (Player players :
                    (ArrayList<Player>) server.getGameInstance().getPlayers().clone()) {
                System.out.println(players.getPlayerName());
                System.out.println(player.getPlayerName());
                if (Objects.equals(players.getPlayerName(), player.getPlayerName())) {
                    server.getGameInstance().getPlayers().remove(players);
                    server.getGameInstance().addPlayer(player);
                    break;
                }
            }
        }
    }
}
