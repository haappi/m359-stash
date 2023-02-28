package io.github.haappi.packets;

import java.util.Locale;

import static io.github.haappi.bold_server.Utils.charAt;

public class Card implements Packet {
    private final String color;
    private final String container;
    private final String size;
    private final String pattern;

    private final String fileName;
    private final boolean isBackCard;

    public Card(String color, String container, String size, String pattern) {
        String fileName1;
        this.color = color;
        this.container=container;
        this.size=size;
        this.pattern = pattern;

        fileName1 = charAt(color, 0) + charAt(container, 0) + charAt(size, 0) + charAt(pattern, 0);
        fileName1 = fileName1.toUpperCase(Locale.ROOT);
        fileName = fileName1;
        isBackCard = false;
    }

    public Card() {
        color = "";
        container = "";
        size = "";
        pattern = "";
        isBackCard = true;
        fileName = "back";
    }
}
