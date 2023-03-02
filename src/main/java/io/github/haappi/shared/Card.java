package io.github.haappi.shared;


import java.util.Locale;

import static io.github.haappi.shared.Utils.charAt;

public class Card implements Packet {
    private final String color;
    private final String container;
    private final String size;
    private final String pattern;

    private final String fileName;
    private Image image;
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

        image = new io.github.haappi.shared.Image("file:src/main/resources/card-images/" + fileName + ".png");
    }

    public String getColor() {
        return color;
    }

    public String getContainer() {
        return container;
    }

    public String getSize() {
        return size;
    }

    public String getPattern() {
        return pattern;
    }

    public String getFileName() {
        return fileName;
    }

    public Image getImage() {
        return image;
    }

    public boolean isBackCard() {
        return isBackCard;
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
